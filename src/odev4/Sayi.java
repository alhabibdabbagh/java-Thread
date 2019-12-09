/**
*
* 
* <p>
* Sayi verisini temsil eden sayı sınıfı
* </p>
*/
package odev4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

/**
 *
 * @author m_dem
 */
public class Sayi {
    public BigInteger veri;
    public Sayi(String gelenveri){
        veri=new BigInteger(gelenveri);
    }
    public Sayi(int gelenveri){
        veri=new BigInteger(String.valueOf(gelenveri));
    }
    public void Yazdir() throws IOException{
        String str = veri.toString();

        File file = new File("Sonuc.txt");
        if (!file.exists()) {
            file.createNewFile();           
        }
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(str);
        bWriter.close();

    }
    
    
}
