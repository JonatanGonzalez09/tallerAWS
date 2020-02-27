package edu.escuelaing.arep.app.operacion;

import edu.escuelaing.arep.app.annotation.Web;

public class Operacion{

    @Web("multiplicacion")
    public static String multiplicacion(String num1, String num2) {
          System.out.println("num1...."+num1);
          return "<head>"
          +"    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css\">"
          +"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js\"></script>"
          +"</head>"
          +"<body>"
          +"    <div class=\"row\">"
          +"        <div class=\"card-panel  blue darken-4 col l6 m6 s6 offset-l3 offset-s3 offset-m3\">"
          +"            <h6 class=\"center-align white-text \">El resultado de multiplicar "+num1 +" y "+num2+" es "+Integer.toString(Integer.parseInt(num1)*Integer.parseInt(num2))+"</h6>"
          +"        </div>"
          +"    </div>"
          +"<div class=\"row\">"
          +"    <div class=\"input-field col l6 m6 s12\">"
          +"        <a class=\"btn waves-effect indigo\" href=\"../index.html\">Volver</a>"
          +"    </div>"
          +"</div>"
          +"</body>";
    }
}