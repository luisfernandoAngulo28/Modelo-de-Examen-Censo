/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package censo2023;

/**
 *Auxiliatura
 * @author fernando Angulo
 */
public class Censo {
    VectorBitsGenerico_final V;
    int cant;
    String nombres[];
    
    public Censo(int cantidad){
        nombres=new String[cantidad];
        V=new VectorBitsGenerico_final(28, cantidad);
        cant=0;
    }
    
    
    public void Insertar(String Nombre,int Edad,char Sexo,int UV,int Distrito, int Dpto,
                                         int Pav,int Agua,int Luz,int Transporte,int Tv){
        
        int mask=Edad;//7
   //System.out.println("mask="+ Integer.toBinaryString(mask));
        int s;
        if (Sexo=='M') {
            s=1;
        }else{
            s=0;
        }
        mask=mask<<1; //1+7=8 bits
        mask=mask|s;
              // System.out.println("mask="+ Integer.toBinaryString(mask));
        mask=mask<<8;
        mask=mask|UV;//8+8=16 bits
      //  System.out.println("mask="+ Integer.toBinaryString(mask));
        mask=mask<<3;
        mask=mask|Distrito;//16+3=19 bits
       //  System.out.println("mask="+ Integer.toBinaryString(mask));
        mask=mask<<4;
        mask=mask|Dpto;//19+4=23
       // System.out.println("mask="+ Integer.toBinaryString(mask));
        //--servicios  / int pav,int Agua,int Luz,int Transporte,int tv)
        mask=mask<<1;
        mask=mask|Pav;//23+1=24bits
        //System.out.println("mask="+ Integer.toBinaryString(mask));
        mask=mask<<1;
        mask=mask|Agua;//24+1=25bits
       // System.out.println("mask="+ Integer.toBinaryString(mask));
        mask=mask<<1;
        mask=mask|Luz;//25+1=26bits
        //System.out.println("mask="+ Integer.toBinaryString(mask));
         mask=mask<<1;
        mask=mask|Transporte;//26+1=27bits
        //System.out.println("mask="+ Integer.toBinaryString(mask));
         mask=mask<<1;
        mask=mask|Tv;//27+1=28bits        
       // System.out.println("mask="+mask+"="+ Integer.toBinaryString(mask));
        nombres[cant]=Nombre;
        V.Insertar(mask, cant+1);
        cant++;
        //System.out.println(V.toString());
    }
    
    //---Get
    
    public String getNombre(int pos){
        return nombres[pos-1];
    }
    //-----------------------------------------------------28 bits
    //pos=1  2^7-1;=127
    public int getEdad(int pos){
        int dato=V.getDato(pos);
        //dato=1100011110000010111100111111
        //28-7=21
        int canbit=(int) (Math.pow(2, 7)-1);
        dato=dato>>>21; //000000000000000000000|1100011|
        dato=dato&canbit;
        return dato;
    }
    
    public char getSexo(int pos){
        int dato=V.getDato(pos);
           //dato=1100011110000010111100111111
            //21-1=20
         int canbit=(int) (Math.pow(2, 1)-1);  
         dato=dato>>>20;
         dato=dato&canbit;
            if (dato==1) {
                return 'M';
            }else{
                return 'F';
            }
    }
    
    public int getUV(int pos){
       int dato=V.getDato(pos);
           //dato=1100011110000010111100111111
            //20-8=12
         int canbit=(int) (Math.pow(2, 8)-1);  
         dato=dato>>>12;
         dato=dato&canbit;
         return dato;
    }
    
    public int getDistrito(int pos){
       int dato=V.getDato(pos);
           //dato=1100011110000010111100111111
            //12-3=9
         int canbit=(int) (Math.pow(2, 3)-1);  
         dato=dato>>>9;
         dato=dato&canbit;
         return dato;
    }
    
    public String getDpto(int pos){
        String dpto="";
       int dato=V.getDato(pos);
           //dato=1100011110000010111100111111
            //9-4=5
         int canbit=(int) (Math.pow(2, 4)-1);  
         dato=dato>>>5;
         dato=dato&canbit;
         switch(dato){
             case 1:  dpto="Santa Cruz"; break;
             case 2:  dpto="La Paz"; break;
             case 3:  dpto="Oruro "; break;
             case 4:  dpto="Chuquisaca"; break;
             case 5:  dpto="Tarija"; break;
             case 6:  dpto="Beni"; break;
             case 7:  dpto="Pando"; break;
             case 8:  dpto="Potosí"; break;
             case 9:  dpto="Cochabamba"; break;
             
         }
         return dpto;
    }
    
    public String getPav(int pos){
       int dato=V.getDato(pos);
           //dato=1100011110000010111100111111
            //5-1=4
         int canbit=(int) (Math.pow(2, 1)-1);  
         dato=dato>>>4;
         dato=dato&canbit;
         if (dato==1) {
             return "Si";
        }else{
             return "No";
         }
    }
    
    public String getAgua(int pos){
       int dato=V.getDato(pos);
           //dato=1100011110000010111100111111
            //4-1=3
         int canbit=(int) (Math.pow(2, 1)-1);  
         dato=dato>>>3;
         dato=dato&canbit;
         if (dato==1) {
             return "Si";
        }else{
             return "No";
         }
    }
    
     public String getLuz(int pos){
       int dato=V.getDato(pos);
           //dato=1100011110000010111100111111
            //3-1=2
         int canbit=(int) (Math.pow(2, 1)-1);  
         dato=dato>>>2;
         dato=dato&canbit;
         if (dato==1) {
             return "Si";
        }else{
             return "No";
         }
    }
       public String getTransporte(int pos){
       int dato=V.getDato(pos);
           //dato=1100011110000010111100111111
            //2-1=1
         int canbit=(int) (Math.pow(2, 1)-1);  
         dato=dato>>>1;
         dato=dato&canbit;
         if (dato==1) {
             return "Si";
        }else{
             return "No";
         }
    }
    
    public String getTV(int pos){
       int dato=V.getDato(pos);
           //dato=1100011110000010111100111111
            //1-1=0
         int canbit=(int) (Math.pow(2, 1)-1);  
         //dato=dato>>>0;
         dato=dato&canbit;
         if (dato==1) {
             return "Si";
        }else{
             return "No";
         }
    }


    
    public String Mostrar(int pos) {
        return "Censo 2023 \n"+
                "nro1="+cant+"\n"+
                "Nombre: "+getNombre(pos)+"\n"+
                "Edad: "+getEdad(pos)+"\n"+
                "sexo: "+getSexo(pos)+"\n"+
                "UV: "+getUV(pos)+"\n"+
                "Distrito: "+getDistrito(pos)+"\n"+
                "Departamento: "+getDpto(pos)+"\n"+
                "Servicios-----------------------"+"\n"+
                "Pav: "+getPav(pos)+"\n"+
                "Agua: "+getAgua(pos)+"\n"+
                "Luz: "+getLuz(pos)+"\n"+
                "Transporte: "+getTransporte(pos)+"\n"+
                 "Tv: "+getTV(pos)+"\n";
                
    }
    public static void main(String[] args) {
          Censo C=new Censo(10);
          C.Insertar("Fernandoo", 99, 'M', 130, 7, 1, 1, 1, 1, 1, 1);
          System.out.println(C.Mostrar(1));
    }
    
    
}
