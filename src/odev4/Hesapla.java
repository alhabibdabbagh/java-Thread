/**
*
* 
* <p>
* Hesaplama işlerini yapan sınıf
* </p>
*/
package odev4;

import java.math.BigInteger;

/**
 *
 *
 */
public class Hesapla extends Thread {
   
    private BigInteger baslangicSayisi;
    private BigInteger bitisSayisi;
    public Sayi Sonuc= new Sayi(1);
    public Hesapla(Sayi baslangic,Sayi bitis){
         baslangicSayisi = baslangic.veri;
         baslangicSayisi=baslangicSayisi.add(BigInteger.valueOf(1));
         bitisSayisi = bitis.veri;
    }
    @Override
    public void run() {       
        int karsilastirma;
        do {          
            karsilastirma=bitisSayisi.compareTo(baslangicSayisi);
            Sonuc.veri=Sonuc.veri.multiply(bitisSayisi);
            
            bitisSayisi=bitisSayisi.add(new BigInteger("-1"));
        } while (karsilastirma==1);        
    }
}