/**
*
* 
* <p>
* Test Sınıfı
* </p>
*/
package odev4;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import odev4.Hesapla;

/**
 *
 * @author m_dem
 */
public class Odev4 {

    public static int[] bol(int sayi){ // verilen sayıyı 5 parçaya ayıran fonksiyon
        int dizi[] =  new  int[5];
        if (sayi>5) {
            int kalan = sayi % 5;
            int bolum = sayi/5;
            for (int i = 0; i < 5; i++) {
                dizi[i]=bolum*(i+1);
            }
            if (kalan!=0) {// sayi 5 e tam bölünmüyorsa(kalan varsa) kalanı son değere ekle
                dizi[4]+=kalan;
            }
            return dizi;
        }else {
             return new int[]{sayi};
        }
        
      
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        Scanner input = new Scanner(System.in);
        int sayi;

        System.out.println("Bir tam sayı giriniz");
        sayi = input.nextInt();
        if (sayi!=0) {
            int dizi[]= bol(sayi);// alınan sayıyı 5 parçaya böl

            Sayi ThreadSonuc = new Sayi(1);
            Hesapla hesapDizi[] = new Hesapla[5]; // hesaplama işini yapan thread extends edilmiş fonksiyonun dizisi
            long toplamThreadSaniye = System.currentTimeMillis();
            if (dizi.length==5) {
                for (int i = 0; i < 5; i++) { // girilen sayi 5ten büyükse 5 e bölündü 5 adet thread oluştur ve hesaplamaya başlat
                    if (i==0) {
                        Hesapla hesaplaic =  new Hesapla(new Sayi(1),new Sayi(dizi[i]));
                        hesaplaic.start();
                        hesapDizi[i]=hesaplaic;
                    }else {
                        Hesapla hesaplaic =  new Hesapla(new Sayi(dizi[i-1]),new Sayi(dizi[i]));
                        hesaplaic.start();
                        hesapDizi[i]=hesaplaic;
                    }
                }
                 while(true) { // threadler bitene kadar dön
                    int kontrol =0;
                    for (Hesapla hesapla:hesapDizi) {
                        if(!hesapla.isAlive()){
                               kontrol++;
                        }
                    }
                    if (kontrol==5) {// tüm threadlerın sayısı 5 kontrol 5 se bitmiştir
                       break; // tüm threadler bitti çık
                    }                 
                }
                for (Hesapla hesapla:hesapDizi) { //gelen 5 thread sonucunu çarp
                   ThreadSonuc.veri=ThreadSonuc.veri.multiply(hesapla.Sonuc.veri);
                }

            }else{ // girilen sayi 5 ten küçükse bol() fonksiyonu sayiyi bölmez ve tek değer olarak dizinin ilk elemanında sayının kendisini gönderir
                Hesapla _hesapla =  new Hesapla(new Sayi(1),new Sayi (dizi[0]));
                _hesapla.start();
                while(true) {
                    if(!_hesapla.isAlive()){
                        ThreadSonuc.veri=_hesapla.Sonuc.veri;
                        break;
                    }
                }
            }
            toplamThreadSaniye = System.currentTimeMillis() - toplamThreadSaniye;


            //Thread Kullanmadan Hesaplama 
            Sayi normalBitis = new Sayi(sayi);      
            Sayi normalSonuc = new Sayi(1);
            int karsilastirma;
            long toplamNormalSaniye =System.currentTimeMillis();
            do {          
                karsilastirma=normalBitis.veri.compareTo(BigInteger.ONE);
                normalSonuc.veri=normalSonuc.veri.multiply(normalBitis.veri);

                normalBitis.veri=normalBitis.veri.add(new BigInteger("-1"));
            }while (karsilastirma==1);        
            toplamNormalSaniye =System.currentTimeMillis()-toplamNormalSaniye;        
              //Thread Kullanmadan Hesaplama 


            System.out.println("Sayi : " + sayi);
            System.out.println("Paralel Hesaplama Süresi : " + toplamThreadSaniye +" milisaniye");
            System.out.println("Seri Hesaplama Süresi : " + toplamNormalSaniye +" milisaniye");

            try {
                ThreadSonuc.Yazdir();
                System.out.println("Sonuç dosyaya yazıldı");
            } catch (IOException ex) {
               System.out.println("Dosyaya yazılırken bir hata oluştu : "+ex.getMessage());
            }
        }
    }
    
}
