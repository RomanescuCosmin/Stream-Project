const statusEl = document.getElementById('status');
const tbody = document.getElementById('tbody');
const wrap = document.getElementById('table-wrap');
const msg = document.getElementById('message');

const statusCity = document.getElementById('statusCity');
const tbodyCity = document.getElementById('tbodyCity');
const wrapCity = document.getElementById('table-wrap_city');
const msgCity = document.getElementById('messageCity');


async function fetchQ1() {
    setStatus('loading');
    msg.textContent = '';
    try {
        const res = await fetch('/stream/exercice/q1',
            {
                headers:
                    {'Accept': 'application/json'}
            });
        if (!res.ok) {
            const text = await res.text();
            throw new Error(text || ('HTTP ' + res.status));
        }
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
        setStatus('ready', `${rows.length} regizori`);
    } catch (err) {
        wrap.hidden = true;
        msg.innerHTML = `<span class="error">Eroare:</span> ${escapeHtml(err.message)}`;
        setStatus('error');
    }
}

function setStatus(state, text = '') {
    if (state === 'loading') {
        statusEl.innerHTML = `<span class="spinner"></span>apel...`;
    } else if (state === 'error') {
        statusEl.textContent = 'eroare';
    } else {
        statusEl.textContent = text ? `gata · ${text}` : 'gata';
    }
}

function escapeHtml(s) {
    return s.replace(/[&<>"']/g, c => ({'&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#039;'}[c]));
}

document.getElementById('run').addEventListener('click', fetchQ1);
document.getElementById('reload').addEventListener('click', fetchQ1);
document.getElementById('runCity').addEventListener('click', fetchQ2);
document.getElementById('reloadCity').addEventListener('click', fetchQ2);
document.getElementById('clear').addEventListener('click', () => {
    tbody.innerHTML = '';
    document.getElementById('table-wrap').hidden = true;
    msg.textContent = '';
    setStatus('ready');
});

document.getElementById('clearCity').addEventListener('click', () => {
    tbody.innerHTML = '';
    document.getElementById('table-wrap').hidden = true;
    msg.textContent = '';
    setStatus('ready');
});


async function fetchQ2() {
    setStatus('loading');
    msgCity.textContent = '';

    try {
        const res = await fetch('/stream/exercice/q2', {headers: {'Accept': 'application/json'}});
        const data = await res.json();

        const rows = Object.entries(data)
            .map(([continent, payload]) => {
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
        setStatus('ready', `${rows.length} orase`);
    } catch (err) {
        wrapCity.hidden = true;
        msgCity.innerHTML = `<span class="error">Eroare:</span> ${escapeHtml(err.message)}`;
        setStatus('error');
    }
}