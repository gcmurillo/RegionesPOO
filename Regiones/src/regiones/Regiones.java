/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regiones;

import java.util.Scanner;

public class Regiones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner scan = new Scanner(System.in);
        
        String info = "C1-Bogota-0.021-5716,5717,5712,5713,5714,5715;"
                       + "C2-Calí-0.019-57265,57290,57289,57288,57268,57264,57266,57255,57260,57233,57237,57241,57242" + 
                        ",57232,57244,57243,57252,57231,57250,57251;"
                       + "C3-Mobile-0.03-57350,57305;"
                       + "C4-Mobile Claro-0.0108-57313,57312,57310,57311,57320,57314,57321,57314,57321,57323,57322;"
                       + "C5-Mobile Movistar-0.0178-57319,57318,57317,57316,57315;";
                       
        String[][] regionesTarifas = new String[5][2];
        String[][] prefijos = new String[5][20];
        
        String[] registros = new String[5];
        
        registros = info.split(";");
        
        
        //TEMA 1
        for(int i = 0; i < registros.length; i++){
            String[] linea = registros[i].split("-");
            regionesTarifas[i][0] = linea[0] + "-" + linea[1];
            regionesTarifas[i][1] = linea[2];
            String[] pre = linea[3].split(",");
            
            for(int j = 0; j<pre.length; j++){
                prefijos[i][j] = pre[j];
            }
        }
        
        //TEMA 2
        for(int i = 0; i<regionesTarifas.length;i++){
            System.out.println("Region: " + regionesTarifas[i][0] + " | Tarifa: " + regionesTarifas[i][1]);
        }
        
        //TEMA 3
        System.out.println("Ingrese tarifa nacional: ");
        float tarifa = scan.nextFloat();
        
        //TEMA 4
        System.out.println("Ingrese código de la region para actualizar tarifa: ");
        String linea = scan.next();
        int regiones = 0;
        for (int i = 0; i < regionesTarifas.length; i++){
            if (regionesTarifas[i][0].startsWith(linea)){
                System.out.println("Ingrese nueva tarifa para la region: " + regionesTarifas[i][0]);
                String nuevaTarifa = scan.next();
                regiones++;
            }
        }
        
        if (regiones == 0) System.out.println("No existe ninguna region con aquel codigo");
        
        //TEMA 5
        System.out.println("Ingrese número Telefonico con el formato prefijo-numero: ");
        String numero = scan.next();
        
        String pre = numero.split("-")[0];
        
        int regionLlamada = 100;
        for (int i = 0; i < prefijos.length; i++){
            for (int j = 0; j<prefijos[i].length; j++){
                if(prefijos[i][j] != null){
                    if (prefijos[i][j].startsWith(pre)){
                        regionLlamada = i;
                    }
                }
            }
        }
        
        if (regionLlamada == 100){
            System.out.println("Region no reconocida, costo por minuto: " + tarifa);
        }
        else{
            System.out.println("Region: " + regionesTarifas[regionLlamada][0] + " costo por minuto: " + regionesTarifas[regionLlamada][1]);
        }
        
        //TEMA 6
        float total = 0;
        float porRegion = 0;
        
        for (int i = 0; i< regionesTarifas.length; i++){
            porRegion = 100 * Float.parseFloat(regionesTarifas[i][1]);
            System.out.println("Costo por: " + regionesTarifas[i][0] + " es: " + porRegion);
            total += porRegion;
        }
        System.out.println("Costo total es: " + total);
        
        //TEMa 7
        float tarifaBogota = Float.parseFloat(regionesTarifas[0][1]);
        float tarifaCali = Float.parseFloat(regionesTarifas[0][1]);
        float nuevoProveedor = 6;
        float ahorro = ((tarifaBogota + tarifaCali)*100) - nuevoProveedor;
        if(ahorro > 0){
            System.out.println("Tiene un ahorro de: " + ahorro + " con el nuevo proveedor");
        }else{
            ahorro = -1 * ahorro;
            System.out.println("Tiene perdida de: " + ahorro);
        }
    }
    
}
