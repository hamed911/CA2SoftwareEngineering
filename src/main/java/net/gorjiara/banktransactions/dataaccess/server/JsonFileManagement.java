/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.dataaccess.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import net.gorjiara.banktransactions.domain.server.CoreBankingManagement;

/**
 *
 * @author Hamed Ara
 */
public class JsonFileManagement {
    private static String fileName="src\\main\\java\\net\\gorjiara\\banktransactions\\dataaccess\\server\\core.json";
    private static JsonFileManagement jsonParser;
    private JsonFileManagement(){
    }
    public static JsonFileManagement getInstance(){
        if(jsonParser==null)
            jsonParser = new JsonFileManagement();
        return jsonParser;
    }
    public CoreBankingManagement readDataFromJsonCore()throws FileNotFoundException{
        Gson gson = new Gson();
        return gson.fromJson(new BufferedReader(new FileReader(fileName)), CoreBankingManagement.class);
    }
    public static void syncJsonCore(CoreBankingManagement bankManagement)throws IOException{
        Gson gson = new GsonBuilder().create();
        Writer writer = new FileWriter("src\\main\\java\\net\\gorjiara\\banktransactions\\dataaccess\\server\\core1.json");
        String json =gson.toJson(bankManagement);
        writer.write(json);
        writer.close();
    }
    public static String toGson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
    public static <T extends Object> T toObject(String objString,Class<T> c){
        Gson gson = new Gson();
        return gson.fromJson(objString, c);
    }
}
