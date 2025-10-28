package medium.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ArrayMapping {

    public static void main(String[] args) throws IOException {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(in);
        StringTokenizer st;
        int n = Integer.parseInt(bf.readLine().trim());
        List<List<Integer>> lines = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int d = Integer.parseInt(st.nextToken());
            List<Integer> row = new ArrayList<>(d);
            for (int j = 0; j < d; j++) {
                row.add(Integer.parseInt(st.nextToken()));
            }
            lines.add(row);
        }

        int q = Integer.parseInt(bf.readLine().trim());
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(bf.readLine());
            int x= Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int xi = x - 1, yi = y -1;

            if(xi >= 0 && xi < lines.size()) {
                List<Integer> row = lines.get(xi);
                if (yi >= 0 && yi < row.size()) {
                    out.append(row.get(yi)).append('\n');
                    continue;
                }
            }
            out.append("ERROR!").append('\n');
        }
        System.out.print(out.toString());



    }

}
