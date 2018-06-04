/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.webserver;

import com.gyver.matrixmover.MatrixMover;
import com.gyver.matrixmover.core.Controller;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonas
 */
public class WebserverHandle implements HttpHandler {
    
    private String website = "<html lang='de'>\n" +
        "<head>\n" +
        "<meta name='viewport' content='width=640' />\n" +
        "<meta name = 'viewport' \n" +
        "content = 'width = 640, <!--Seite auf 1100 Pixel skalieren-->\n" +
        "user-scalable = no,  <!-- Darf der User zoomen? yes/no -->\n" +
        "initial-scale = 1,  <!-- Minimaler Skalierungsfaktor -->\n" +
        "maximum-scale = 1 <!-- Maximaler Skalierungsfaktor. Hier 100% = scharfe Pixeldarstellung -->\n" +
        "'/>\n" +
        "<title>MatrixMover</title>\n" +
        "    <style type='text/css'>\n" +
        "    body {\n" +
        "	font-family:Arial;\n" +
        "	font-size:24px;\n" +
        "	line-height:100%;\n" +
        "    }\n" +
        "    table {\n" +
        "            width:600px;\n" +
        "    }	\n" +
        "    td {\n" +
        "            height:250px;\n" +
        "            padding:5px;\n" +
        "            text-align:center;\n" +
        "            vertical-align:bottom;\n" +
        "    }\n" +
        "    .myButton {\n" +
        "            -moz-box-shadow:inset 0px 1px 0px 0px #bbdaf7;\n" +
        "            -webkit-box-shadow:inset 0px 1px 0px 0px #bbdaf7;\n" +
        "            box-shadow:inset 0px 1px 0px 0px #bbdaf7;\n" +
        "            background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #79bbff), color-stop(1, #378de5));\n" +
        "            background:-moz-linear-gradient(top, #79bbff 5%, #378de5 100%);\n" +
        "            background:-webkit-linear-gradient(top, #79bbff 5%, #378de5 100%);\n" +
        "            background:-o-linear-gradient(top, #79bbff 5%, #378de5 100%);\n" +
        "            background:-ms-linear-gradient(top, #79bbff 5%, #378de5 100%);\n" +
        "            background:linear-gradient(to bottom, #79bbff 5%, #378de5 100%);\n" +
        "            filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#79bbff', endColorstr='#378de5',GradientType=0);\n" +
        "            background-color:#79bbff;\n" +
        "            -moz-border-radius:10px;\n" +
        "            -webkit-border-radius:10px;\n" +
        "            border-radius:10px;\n" +
        "            border:1px solid #84bbf3;\n" +
        "            display:inline-block;\n" +
        "            cursor:pointer;\n" +
        "            color:#ffffff;\n" +
        "            font-family:Arial;\n" +
        "            font-size:48px;\n" +
        "            font-weight:bold;\n" +
        "            text-decoration:none;\n" +
        "            width:100%;\n" +
        "            height:100%;\n" +
        "            padding:90px 0px;\n" +
        "    }\n" +
        "    </style>\n" +
        "</head>\n" +
        "<body>\n" +
        "<table>\n" +
        "<tr>\n" +
        "<td>\n" +
        "      <a href='http://192.168.0.105:8000/matrixmover?mode=chill' class='myButton'>Load CHILL Preset </a><br>\n" +
        "</td></tr>\n" +
        "<tr><td>\n" +
        "      <a href='http://192.168.0.105:8000/matrixmover?mode=party' class='myButton'>Load PARTY Preset </a><br>\n" +
        "</td></tr>\n" +
        "<tr><td>\n" +
        "      <a href='http://192.168.0.105:8000/matrixmover?mode=simple' class='myButton'>Load Simple Preset </a><br>\n" +
        "</td></tr>\n" +
        "</table>\n" +
        "</body>\n" +
        "</html>\n";
    
    @Override
    public void handle(HttpExchange t) throws IOException {
        // get the request
        String response = t.getRequestURI().getQuery();

        // parse the request an propagate actions to engine
        String[] Split = response.split("&");
        if (Split.length > 0) {
            String[] elemSplit = Split[0].split("=");
            if (elemSplit.length > 1) {
                if (elemSplit[0].equals("mode")) {
                    if (elemSplit[1].equals("chill")) {
                        File sceneDir = new File("scenesChill/");
                        Controller.getControllerInstance().startAutoSceneCycler(MatrixMover.AUTO_TIMER_STAY_TIME, sceneDir);
                    } else if (elemSplit[1].equals("party")) {
                        File sceneDir = new File("scenesParty/");
                        Controller.getControllerInstance().startAutoSceneCycler(MatrixMover.AUTO_TIMER_STAY_TIME, sceneDir);
                    } else if (elemSplit[1].equals("simple")) {
                        Controller.getControllerInstance().stopAutoSceneCycler();
                        Controller.getControllerInstance().loadSimpleScene();
                    } else if (elemSplit[1].equals("random")) {
                        Controller.getControllerInstance().stopAutoSceneCycler();
                        Controller.getControllerInstance().loadNoiseScene();
                    } 
                } 
                else if (elemSplit[0].equals("color")) {
                    Color newCol = Color.decode(elemSplit[1]);
                    Controller.getControllerInstance().setGlobalColor(newCol);
                }
            }
            
        }


        response = website;

        t.sendResponseHeaders(200, response.length());
        try (OutputStream os = t.getResponseBody()) {
            os.write(response.getBytes());
            // write html code from resource to stream
            //URI uri = getClass().getResource("/webserverUI.html").toURI();
            //byte[] code = Files.readAllBytes(Paths.get(uri));
            //String content = readFileToString(new File("data/webserverUI.html"));
            
            //os.write(Files.readAllBytes(Paths.get("data/webserverUI.html")));
        }
        
    }
}

