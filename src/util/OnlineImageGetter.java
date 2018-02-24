package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class OnlineImageGetter {

    public OnlineImageGetter(){

    }

    public static void main(String[] args)
    {
        try {
            getImage("Paul_Lindelauf");
            BufferedImage image = ImageIO.read(new File("Resources\\OnlineImages\\" + "Paul_Lindelauf" + ".jpg"));
            ImageIcon ii = new ImageIcon(image.getScaledInstance(200,100,1));
            JLabel label = new JLabel(ii);
            JOptionPane.showMessageDialog(null, label);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String getImage(String searchName) throws Exception {
        if (searchName.contains(" ")){
            String[] newSearchName = searchName.split(" ");
            boolean firstTimeIsPassed = false;
            for (String partOfNewSearchName : newSearchName){
                if (firstTimeIsPassed){
                    searchName = searchName + "_" + partOfNewSearchName;
                }
                else{
                    searchName = partOfNewSearchName;
                    firstTimeIsPassed = true;
                }
            }
        }

        if (new File("Resources\\OnlineImages\\" + searchName + ".jpg").exists()){
            return "Resources\\OnlineImages\\" + searchName + ".jpg";
        }
        else{

            //establish a connection with the url to get the jpeg.
            URL url = new URL("https://www.bing.com/images/search?q=" + searchName + "&FORM=HDRSC2");
            URLConnection connection = url.openConnection();

            //reads out the html-code.
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lineWithImageLink = "";
            while ((line = reader.readLine()) != null) {
                if (line.contains("SearchBoxAnswer")) {
                    lineWithImageLink = line;
                }
            }
            //splits all string so only the jpeg file can be read.
            String[] split = lineWithImageLink.split("href=");
            String link = "http://access2school.com/img/profile_pic.jpg";
            for (String searchForJpg : split){
                if (searchForJpg.contains(".jpg")){
                   link = searchForJpg;
                   break;
                }

            }
            String[] splitLink = link.split("\"");
            String[] splitBackSlash = splitLink[1].split("/");
            int length = splitBackSlash.length;
            String path = "Resources\\OnlineImages\\" + searchName +  ".jpg";
            //writes image to path.
            try {
                URL imageUrl = new URL(splitLink[1]);
                InputStream inputStream = new BufferedInputStream(imageUrl.openStream());
                OutputStream out = new BufferedOutputStream(new FileOutputStream(path));
                for (int i; (i = inputStream.read()) != -1; ) {
                    out.write(i);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return path;
        }

    }
}
