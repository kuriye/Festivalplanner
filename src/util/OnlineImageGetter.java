package util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class OnlineImageGetter {

    public OnlineImageGetter(){

    }

    public String getImage(String searchName) throws Exception {

        //change spaces to plusses.
        if (searchName.contains(" ")){
            String[] newSearchName = searchName.split(" ");
            boolean firstTimeIsPassed = false;
            for (String partOfNewSearchName : newSearchName){
                if (firstTimeIsPassed){
                    searchName = searchName + "+" + partOfNewSearchName;
                }
                else{
                    searchName = searchName + partOfNewSearchName;
                    firstTimeIsPassed = true;
                }
            }
        }

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
        String[] splitLink = split[1].split("\"");
        String[] splitBackSlash = splitLink[1].split("/");
        int length = splitBackSlash.length;
        String path = "Resources\\OnlineImages\\" + splitBackSlash[length - 1];
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
