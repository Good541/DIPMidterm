import java.util.Scanner;
class Midtermquest{
    public static void main(String[] args) {
        BarcodeGenerator im = new BarcodeGenerator();
        String barcodedata = "60050215:H4S0B2:";
        Scanner sc = new Scanner(System.in);
        String passcode = "";
        boolean correctchar = false;

        im.barcodeTable();//Generate barcode data

        System.out.println("My code is "+barcodedata);
        //Check passcode character
        while (true) 
        {
            System.out.print("Enter GM secret code (4 digit number): ");
            passcode = sc.nextLine();
            for (int i = 0; i< passcode.length(); i++){
                correctchar = false;
                for(int j = 16; j < 26; j++){
                    correctchar = false;
                    String charcode = Character.toString(passcode.charAt(i));
                    if (charcode.equals(im.barcodetable[j][0])){ 
                        correctchar = true;
                        if(charcode.equals("?")){
                            System.out.println("Warning: Unknown character will equal \"?\" character.");
                        }
                        break;          
                    }
                }
                if(correctchar == false){
                    break;
                }
            }
            if (passcode.length() == 4 && correctchar == true)
            {
                barcodedata = barcodedata.concat(passcode);
                break;
            }
            System.out.println("Passcode invalid! Please try again.");                
        }   
        im.generateBarcode128(barcodedata);
        im.write("Barcode_60050215_H4S0B2_"+passcode+".bmp");
        System.out.println("Barcode directory is \""+System.getProperty("user.dir")+"\\Barcode_60050215_H4S0B2_"+passcode+".bmp\"");
    }
}