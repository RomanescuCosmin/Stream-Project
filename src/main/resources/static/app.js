const statusEl = document.getElementById('status');
const tbody = document.getElementById('tbody');
const wrap = document.getElementById('table-wrap');
const msg = document.getElementById('message');

const statusCity = document.getElementById('statusCity');
const tbodyCity = document.getElementById('tbodyCity');
const wrapCity = document.getElementById('table-wrap_city');
const msgCity = document.getElementById('messageCity');

// Q3: elemente UI (asum că ai secțiunea în HTML)
const statusGenres = document.getElementById('statusGenres');
const tbodyGenres = document.getElementById('tbodyGenres');
const wrapGenres = document.getElementById('table-wrap_genres');
const msgGenres = document.getElementById('messageGenres');

// helper: status per secțiune
function setStatusFor(el, state, text = '') {
    if (state === 'loading') {
        el.innerHTML = `<span class="spinner"></span>apel...`;
    } else if (state === 'error') {
        el.textContent = 'eroare';
    } else {
        el.textContent = text ? `gata · ${text}` : 'gata';
    }
}

function escapeHtml(s) {
    return s.replace(/[&<>"']/g, c => ({'&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#039;'}[c]));
}

// Q1
async function fetchQ1() {
    setStatusFor(statusEl, 'loading');
    msg.textContent = '';
    try {
        const res = await fetch('/stream/exercice/q1', {headers: {'Accept': 'application/json'}});
        if (!res.ok) throw new Error(await res.text() || ('HTTP ' + res.status));
        const data = await res.json();

        const rows = Object.entries(data).sort((a, b) => b[1] - a[1]);

        tbody.innerHTML = '';
        rows.forEach(([director, count], idx) => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
          <td>${idx + 1}</td>
          <td>${escapeHtml(director)}</td>
          <td class="right">${count}</td>`;
            tbody.appendChild(tr);
        });

        wrap.hidden = rows.length === 0;
        if (rows.length === 0) msg.textContent = 'Nu s-au găsit rezultate.';
        setStatusFor(statusEl, 'ready', `${rows.length} regizori`);
    } catch (err) {
        wrap.hidden = true;
        msg.innerHTML = `<span class="error">Eroare:</span> ${escapeHtml(err.message)}`;
        setStatusFor(statusEl, 'error');
    }
}

// Q2
async function fetchQ2() {
    setStatusFor(statusCity, 'loading');
    msgCity.textContent = '';
    try {
        const res = await fetch('/stream/exercice/q2', {headers: {'Accept': 'application/json'}});
        if (!res.ok) throw new Error(await res.text() || ('HTTP ' + res.status));
        const data = await res.json();

        const rows = Object.entries(data)
            .map(([continent, payload]) => {
                // dacă backend-ul întoarce Optional<ContinentCityPair>, Jackson poate trimite fie obiect cu {city: {...}}, fie direct City
                const city = payload?.city ?? payload;
                return [continent, city];
            })
            .filter(([, city]) => !!city)
            .sort((a, b) => (b[1].population ?? 0) - (a[1].population ?? 0));

        tbodyCity.innerHTML = '';
        rows.forEach(([continent, city], idx) => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
          <td>${idx + 1}</td>
          <td>${escapeHtml(continent)}</td>
          <td class="right">${escapeHtml(city.name)} · ${Number(city.population).toLocaleString('ro-RO')} loc.</td>`;
            tbodyCity.appendChild(tr);
        });

        wrapCity.hidden = rows.length === 0;
        if (rows.length === 0) msgCity.textContent = 'Nu s-au găsit rezultate.';
        setStatusFor(statusCity, 'ready', `${rows.length} orase`);
    } catch (err) {
        wrapCity.hidden = true;
        msgCity.innerHTML = `<span class="error">Eroare:</span> ${escapeHtml(err.message)}`;
        setStatusFor(statusCity, 'error');
    }
}



async function fetchQ3() {
    setStatusFor(statusGenres, 'loading');
    msgGenres.textContent = '';

    try {
        const res = await fetch('/stream/exercice/q3', {
            headers: { 'Accept': 'application/json' }
        });

        if (!res.ok) throw new Error(await res.text() || ('HTTP ' + res.status));

        const data = await res.json();

        // sortare descrescător după număr de genuri
        const rows = Object.entries(data)
            .map(([director, genres]) => ({ director, genres }))
            .sort((a, b) => b.genres.length - a.genres.length);

        tbodyGenres.innerHTML = '';
        rows.forEach((entry, idx) => {
            const tr = document.createElement('tr');
            const genreList = entry.genres.join(', ');

            tr.innerHTML = `
                <td>${idx + 1}</td>
                <td>${escapeHtml(entry.director)}</td>
                <td class="right">${entry.genres.length}</td>
                <td>${escapeHtml(genreList)}</td>
            `;
            tbodyGenres.appendChild(tr);
        });

        wrapGenres.hidden = rows.length === 0;
        if (rows.length === 0) msgGenres.textContent = 'Nu s-au găsit rezultate.';
        setStatusFor(statusGenres, 'ready', `${rows.length} regizori`);
    } catch (err) {
        wrapGenres.hidden = true;
        msgGenres.innerHTML = `<span class="error">Eroare:</span> ${escapeHtml(err.message)}`;
        setStatusFor(statusGenres, 'error');
    }
}

// butoane
document.getElementById('run').addEventListener('click', fetchQ1);
document.getElementById('reload').addEventListener('click', fetchQ1);
document.getElementById('clear').addEventListener('click', () => {
    tbody.innerHTML = '';
    wrap.hidden = true;
    msg.textContent = '';
    setStatusFor(statusEl, 'ready');
});

document.getElementById('runCity').addEventListener('click', fetchQ2);
document.getElementById('reloadCity').addEventListener('click', fetchQ2);
document.getElementById('clearCity').addEventListener('click', () => {
    tbodyCity.innerHTML = '';
    wrapCity.hidden = true;
    msgCity.textContent = '';
    setStatusFor(statusCity, 'ready');
});

// Q3 butoane (asum că există în HTML)
document.getElementById('runGenres')?.addEventListener('click', fetchQ3);
document.getElementById('reloadGenres')?.addEventListener('click', fetchQ3);
document.getElementById('clearGenres')?.addEventListener('click', () => {
    tbodyGenres.innerHTML = '';
    wrapGenres.hidden = true;
    msgGenres.textContent = '';
    setStatusFor(statusGenres, 'ready');
});


const statusGenresCount = document.getElementById('statusGenresCount');
const tbodyGenresCount = document.getElementById('tbodyGenresCount');
const wrapGenresCount = document.getElementById('table-wrap_genresCount');
const msgGenresCount = document.getElementById('messageGenresCount');

function extractNameKey(k) {
    if (k == null) return '';
    if (typeof k === 'string') {
        const m = k.match(/name['"]?\s*[:=]\s*['"]?([^,'"}]+)/i);
        return m ? m[1] : k;
    }
    if (typeof k === 'object') {
        if ('name' in k) return String(k.name);
        try { return JSON.stringify(k); } catch { return String(k); }
    }
    return String(k);
}

async function fetchQ3Different() {
    setStatusFor(statusGenresCount, 'loading');
    msgGenresCount.textContent = '';
    try {
        const res = await fetch('/stream/exercice/q3-different', { headers: { 'Accept': 'application/json' } });
        if (!res.ok) throw new Error(await res.text() || ('HTTP ' + res.status));
        const data = await res.json();
        const rows = [];
        if (data && typeof data === 'object') {
            Object.entries(data).forEach(([directorKey, genresMap]) => {
                const director = extractNameKey(directorKey);
                if (genresMap && typeof genresMap === 'object') {
                    Object.entries(genresMap).forEach(([genreKey, count]) => {
                        const genre = extractNameKey(genreKey);
                        rows.push({ director, genre, count: Number(count) || 0 });
                    });
                }
            });
        }
        rows.sort((a,b)=> b.count - a.count || a.director.localeCompare(b.director) || a.genre.localeCompare(b.genre));
        tbodyGenresCount.innerHTML = '';
        rows.forEach((r, i) => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
        <td>${i + 1}</td>
        <td>${escapeHtml(r.director)}</td>
        <td>${escapeHtml(r.genre)}</td>
        <td class="right">${r.count}</td>`;
            tbodyGenresCount.appendChild(tr);
        });
        wrapGenresCount.hidden = rows.length === 0;
        if (rows.length === 0) msgGenresCount.textContent = 'Nu s-au găsit rezultate.';
        setStatusFor(statusGenresCount, 'ready', `${rows.length} rânduri`);
    } catch (err) {
        wrapGenresCount.hidden = true;
        msgGenresCount.innerHTML = `<span class="error">Eroare:</span> ${escapeHtml(err.message)}`;
        setStatusFor(statusGenresCount, 'error');
    }
}

document.getElementById('runGenresCount')?.addEventListener('click', fetchQ3Different);
document.getElementById('reloadGenresCount')?.addEventListener('click', fetchQ3Different);
document.getElementById('clearGenresCount')?.addEventListener('click', () => {
    tbodyGenresCount.innerHTML = '';
    wrapGenresCount.hidden = true;
    msgGenresCount.textContent = '';
    setStatusFor(statusGenresCount, 'ready');
});
