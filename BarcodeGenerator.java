import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

class BarcodeGenerator {
    public int width;
    public int height;

    private BufferedImage img;

    private int barcodewidth;
    private int barcodehight;
    public String[][] barcodetable;
    private int x;
    private int y;

    public boolean write(String fileName){
        try {
            ImageIO.write(img, "bmp", new File(fileName));
            return true;
        } catch (IOException e) {
            return false;
        }
        catch(NullPointerException e){
            return false;
        }
    }
    
    public void generateBarcode128(String barcodedata){
        width = 1450;//Image width
        height = 450;//Image height
        int color = (0 << 16) | (0 << 8) | 0;//Black

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();

        // fill all the image with white
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.white);

        barcodewidth = 5;
        barcodehight = 300;

        //Alignment center
        x = (img.getWidth() - (barcodewidth * barcodedata.length() * 11)) / 2;
        y = (img.getHeight() - barcodehight) / 2;
        
        //Generate start barcode
        x -= (4 * barcodewidth);
        for(int i = 0;i <= 3; i++){
            //Draw black barcode
            switch(i){
                case 0:
                case 1:
                case 3:
                    //Draw Y axis
                    for(int j = 0;j < barcodehight; j++){
                        //Draw X axis
                        for(int m = 0; m < barcodewidth; m++){
                            img.setRGB(x, y, color);
                            x++;
                        }
                        //Set next position to draw
                        x -= barcodewidth;
                        y++;                      
                    }
                    break;
            }
            //Set next position to draw next barcode
            y = (img.getHeight() - barcodehight) / 2;
            x += barcodewidth;
        }

        //Generate barcode from input
        for(int i = 0; i < barcodedata.length(); i++){
            String charcode = Character.toString(barcodedata.charAt(i));
            for(int j = 0; j < barcodetable.length; j++){
                //Check character from table
                if (charcode.equals(barcodetable[j][0])){                
                    for(int k = 0; k < 11;k++){
                        String bit = Character.toString(barcodetable[j][1].charAt(k));
                        //Draw black barcode
                        if(bit.equals("1")) { 
                            //Draw Y axis
                            for(int l = 0; l < barcodehight; l++){
                                //Draw X axis
                                for(int m = 0; m < barcodewidth; m++){
                                    img.setRGB(x, y, color);
                                    x++;
                                }
                                //Set next position to draw
                                x -= barcodewidth;
                                y++;                      
                            }
                        }
                        //Set next position to draw next barcode
                        y = (img.getHeight() - barcodehight) / 2;
                        x += barcodewidth;
                    }     
                    break;
                } 
            }
        }
        
        //Generate Stop barcode
        for(int i = 0;i <= 3; i++){
            //Draw black barcode
            switch(i){
                case 0:
                case 2:
                case 3:
                    //Draw Y axis
                    for(int j = 0;j < barcodehight; j++){
                        //Draw X axis
                        for(int m = 0; m < barcodewidth; m++){
                            img.setRGB(x, y, color);
                            x++;
                        }
                        //Set next position to draw
                        x -= barcodewidth;
                        y++;                      
                    }
                    break;
            }
            //Set next position to draw next barcode
            y = (img.getHeight() - barcodehight) / 2;
            x += barcodewidth;
        }    
    }

    public void barcodeTable(){
        barcodetable = new String[][]
        {
            {" ","11011001100"},//0
            {"!","11001101100"},//1
            {"\"","11001100110"},//2
            {"#","10010011000"},//3
            {"$","10010001100"},//4
            {"%","10001001100"},//5
            {"&","10011001000"},//6
            {"'","10011000100"},//7
            {"(","10001100100"},//8
            {")","11001001000"},//9
            {"*","11001000100"},//10
            {"+","11000100100"},//11
            {",","10110011100"},//12
            {"-","10011011100"},//13
            {".","10011001110"},//14
            {"/","10111001100"},//15
            {"0","10011101100"},//16
            {"1","10011100110"},//17
            {"2","11001110010"},//18
            {"3","11001011100"},//19
            {"4","11001001110"},//20
            {"5","11011100100"},//21
            {"6","11001110100"},//22
            {"7","11101101110"},//23
            {"8","11101001100"},//24
            {"9","11100101100"},//25
            {":","11100100110"},//26
            {";","11101100100"},//27
            {"<","11100110100"},//28
            {"=","11100110010"},//29
            {">","11011011000"},//30
            {"?","11011000110"},//31
            {"@","11000110110"},//22
            {"A","10100011000"},//33
            {"B","10001011000"},//34
            {"C","10001000110"},//35
            {"D","10110001000"},//36
            {"E","10001101000"},//37
            {"F","10001100010"},//38
            {"G","11010001000"},//39
            {"H","11000101000"},//40
            {"I","11000100010"},//41
            {"J","10110111000"},//42
            {"K","10110001110"},//43
            {"L","10001101110"},//44
            {"M","10111011000"},//45
            {"N","10111000110"},//46
            {"O","10001110110"},//47
            {"P","11101110110"},//48
            {"Q","11010001110"},//49
            {"R","11000101110"},//50
            {"S","11011101000"},//51
            {"T","11011100010"},//52
            {"U","11011101110"},//53
            {"V","11101011000"},//54
            {"W","11101000110"},//55
            {"X","11100010110"},//56
            {"Y","11101101000"},//57
            {"Z","11101100010"},//58
            {"[","11100011010"},//59
            {"\\","11101111010"},//60
            {"]","11001000010"},//61
            {"^","11110001010"},//62
            {"_","10100110000"},//63
        };
    }
}