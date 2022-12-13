/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package censo2023;


public class VectorBitsGenerico_final {
     public int V[];
    int cantidad;
    int cantidadbits;
    ///5,7
    //V[000100][00000][00000][00000][00000][00000][00000]
    //V[4][0][0][0][0][0][0]
    //    0   1      2      3        4     5       6
    public VectorBitsGenerico_final(int cantidadbits,int cantidad){
        this.cantidad=cantidad;
        this.cantidadbits=cantidadbits;
        int x = cantidad * cantidadbits;
        int NE = x / 32;
        if( ( x % 32 ) != 0 ){ // falta
            NE++;
        }
        V = new int[NE];
    }
    public void Insertar(int Dato,int pos){
        int Nb = Nbit(pos);
        int NE = NEnt(pos);
        int Dato1 = Dato;
        int mask = (int) (Math.pow(2, cantidadbits+1)-1);
        mask = mask << Nb - 1;
        mask = ~ mask;
        V[NE] = V[NE] & mask; //Borra
        Dato = Dato << Nb - 1;
        V[NE] = V[NE] | Dato;
        
        if (Nb+cantidadbits > 32) // falta
        {
            int Bfalta = ((Nb + cantidadbits) - 32 ) - 1;
            int mask1 = (int) (Math.pow(2, cantidadbits + 1 )-1);
            mask1 = ~ ( mask >>> ( cantidadbits - Bfalta ) );
            V[NE + 1] = V[NE + 1] & mask1;
            Dato1 = Dato1 >>> (cantidadbits - Bfalta);
            V[NE + 1] = V[NE + 1] | Dato1;
            
        }    
    }
    public int Nbit (int pos){
        return ((((pos - 1) * cantidadbits) % 32) + 1);
    }
    public int NEnt(int pos){
        return ((pos - 1) * cantidadbits) / 32;
    }
    public int getDato(int pos){
        int Nb = Nbit(pos);
        int Ne = NEnt(pos);
        int mask = (int) (Math.pow(2,cantidadbits)-1);
        mask = mask << Nb - 1;
        mask = mask & V[Ne];
        mask = mask >>> Nb - 1;
        if (Nb + cantidadbits > 32) // falta
        {
            int bitfalta = (Nb + cantidadbits - 32) - 1;
            int mask1 = (int) (Math.pow(2, cantidadbits)-1);
            mask1 = mask1 >>> (cantidadbits - bitfalta);
            mask1 = mask1 & V[Ne + 1];
            mask1 = mask1 << (cantidadbits - bitfalta);
            mask = mask | mask1;
        }    
        return mask;
    }

    @Override
    public String toString()
    {
        String s = "";
        for (int i = 0 ; i < cantidad; i++) {
            s = s + " " + getDato(i+1);
        }
        return s;
    }        
  
    public static void main (String[] args){
        //TODO code application logic here
        int cantidadbits = 13;
        int cantidad = 8;
        VectorBitsGenerico_final x = new VectorBitsGenerico_final(cantidadbits, cantidad);
        x.Insertar(5, 1);
        x.Insertar(6, 2);
        x.Insertar(7, 3);
        x.Insertar(8, 4);
        x.Insertar(115, 5);         
        x.Insertar(2, 6);         
        x.Insertar(20, 7);         
        x.Insertar(19, 8); 
        System.out.println(x.toString());       
       
        
        
    }
}
