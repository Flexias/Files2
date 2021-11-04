package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        File dirSave = new File("E://Games//savegames");
        String waySave = "E://Games//savegames//save.dat";
        String watZip = "E://Games//savegames//zip.zip";
        GameProgress gameProgress1 = new GameProgress(60, 3, 20, 8.6);
        GameProgress gameProgress2 = new GameProgress(25, 5, 80, 35.3);
        GameProgress gameProgress3 = new GameProgress(52, 2, 35, 12.4);
        saveGame(waySave,gameProgress1);
        zipFiles(watZip,waySave);
        clearFiles(dirSave);


    }

    public static void saveGame(String way, GameProgress gameProgress){
        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(gameProgress);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String wayZip, String waySave){
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(wayZip));
        FileInputStream fis = new FileInputStream(waySave)) {
            ZipEntry entry = new ZipEntry("packed_save.dat");
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void clearFiles(File dir){
        if (dir.isDirectory()){
            for (File item : dir.listFiles()){
                if (item.getName().equals("zip.zip") == false){
                    item.delete();
                }
            }
        }
    }
}
