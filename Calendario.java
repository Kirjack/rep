import java.text.*;
import java.util.*;

public class Calendario
{
    public static void main(String[] args)
    {
        System.out.println("<!doctype html>");
        System.out.println("<html>");
        System.out.println("<head>");
        System.out.println("<meta charset=\"UTF-8\"/>");
        style(); /*Estilos*/
        System.out.println("</head>");
        body(); /*Script*/
        System.out.println("</html>");
    }

    static void style()
    {
        int i;
        System.out.println("<style>");
        for (i=0; i<12; i++)
        {
            System.out.println("." + monthNames[i] + " { background-image: url(\"imagenes/img"+i+".png\"); }");
        }
        System.out.println("* { margin: 0px; padding: 0px; }");
        System.out.println("div { width: 720px; margin: auto; }");
        System.out.println("table { width: 200px; float: left; margin: 20px; }");
        System.out.println("td { text-align:center; width: 20px; height: 20px; }");
        System.out.println("h1, h2 { text-align:center; }");
        System.out.println("</style>");
    }

    static void body()
    {
        System.out.println("<body>");
        printCalendar(2019, 1);
        System.out.println("</body>");
    }

    static String[] monthNames = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    static void printCalendar(int year, int nCols)
    {

        if (nCols < 1 || nCols > 12)
            throw new IllegalArgumentException("Numero de columnas incorrecto!!");
        Calendar date = new GregorianCalendar(year, 0, 1);

        int nRows = (int) Math.ceil(12.0 / nCols);
        int offs = date.get(Calendar.DAY_OF_WEEK) - 2;
        int w = nCols * 24;

        String[][] mons = new String[12][8];

        for (int m = 0; m < 12; m++)
        {
            String name = "<td colspan=\"7\">" + monthNames[m] + "</td>";
            int len = 11 + name.length() / 2;

            mons[m][0] = name;
            mons[m][1] = " <td>Lu</td> <td>Ma</td> <td>Mi</td> <td>Ju</td> <td>Vi</td> <td>Sa</td> <td>Do</td>";

            int dim = date.getActualMaximum(Calendar.DAY_OF_MONTH);

            for (int d = 1; d < 43; d++)
            {
                boolean isDay = d > offs && d <= offs + dim;
                String entry = isDay ? "<td>" + (d - offs) + "</td>" : "<td>   </td>";

                if (d % 7 == 1)
                    mons[m][2 + (d - 1) / 7] = entry;

                else
                    mons[m][2 + (d - 1) / 7] += entry;
            }

            offs = (offs + dim) % 7;
            date.add(Calendar.MONTH, 1);
        }

        System.out.print("<h1>[[ Calendario ]]</h1>");
        System.out.print("<h2>" + year + "</h2>");

        System.out.println("<div>");
        for (int r = 0; r < nRows; r++)
        {
            System.out.println("<table class=\""+ monthNames[r] + "\">");
            for (int i = 0; i < 8; i++)
            {
                System.out.println("<tr>");
                for (int c = r * nCols; c < (r + 1) * nCols && c < 12; c++)
                {
                    System.out.print(mons[c][i]);
                }
                System.out.println("</tr>");
            }
            System.out.println("</table>");
        }
        System.out.println("</div>");
    }
}