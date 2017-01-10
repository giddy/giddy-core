package com.riders.giddy.dispatcher;

import com.graphhopper.matching.EdgeMatch;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import licence.business.routing.GPXMatcher;
import licence.business.routing.GraphUtils;
import licence.model.geo.RouteEdge;
import licence.model.geo.SessionFiles;
import licence.model.geo.StatsDescriptor;

import static java.lang.Math.toIntExact;

/**
 * Created by tothe on 5/25/16.
 */
@Service
public class Resolution {


    public static final String GITHUB_CLIENT_DATA_DIRECTORY = "/home/tothe/Documents/work/githubStaticPage/tudorbeleuta.github.io/res/data/";
    public static final String CLIENT_DATA_DIRECTORY = "/home/tothe/Documents/work/githubStaticPage/tudorbeleuta.github.io/res/data/";

    public static final String MAP_DATA_VARNAME = "mapList";


    static int sessionNo = 0;

    @Autowired
    GraphUtils graphUtils;

    @Autowired
    GPXMatcher gpxMatcher;


    JSONArray statsArray;
    long maxEdgeTraversals = 0;
    String lastModified;

    //check xml validation by dom traversal, should throw an exception if structure is bad
    public static boolean checkXmlFile(File fXmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXmlFile);
        return true;
    }

    public static void addStats(StatsDescriptor clientStats, File descriptor) {
        //StatsDescriptor stats=null;
        try {
            JSONParser parser;
            parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(new FileReader(descriptor));
            JSONObject jsonStats = (JSONObject) obj.get("stats");


            //"stats":{"safe":0,"touristy":0,"recreational":0,"fast":0,"challenging":0,"crowded":0}

            clientStats.addStats(
                    toIntExact((Long) jsonStats.get(StatsDescriptor.SAFE)),
                    toIntExact((Long) jsonStats.get(StatsDescriptor.TOURISTY)),
                    toIntExact((Long) jsonStats.get(StatsDescriptor.RECREATIONAL)),
                    toIntExact((Long) jsonStats.get(StatsDescriptor.FAST)),
                    toIntExact((Long) jsonStats.get(StatsDescriptor.CHALLENGING)),
                    toIntExact((Long) jsonStats.get(StatsDescriptor.CROWDED))
            );
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map<Integer, StatsDescriptor> assessGpxLogs(List<SessionFiles> sessions) {
        System.out.println("received latest sessions...");

        Resolution resolution = new Resolution();
        Map<Integer,StatsDescriptor> edgeRepo=new HashMap<>();
        System.out.println("start point matching to edges");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        sessions.forEach(session -> {

            try {
                File fXmlFile = session.getGpxLogs();

                if (resolution.checkXmlFile(fXmlFile)) {
                    lastModified = fXmlFile.getName();
                    List<EdgeMatch> matches = gpxMatcher.importGpx(session.getGpxLogs().getCanonicalPath(), "bike");
                    matches.forEach(edgeMatch -> {

                        int edgeId = graphUtils.getEdgeIdByMatch(edgeMatch);
                        int baseId = graphUtils.getBaseNodeIdByMatch(edgeMatch);
                        int adjId=graphUtils.getAdjNodeIdByMatch(edgeMatch);
                        StatsDescriptor userStats;
                        if(edgeRepo.containsKey(baseId)){
                            userStats=edgeRepo.get(baseId);

                        }else{
                            userStats=new StatsDescriptor();

                            RouteEdge edge=new RouteEdge(edgeId,adjId,baseId);
                            edge.setDescriptor(userStats);
                            userStats.setEdge(edge);
                        }
                        addStats(userStats,session.getDescriptor());

                        edgeRepo.put(baseId,userStats);

                    });
                    sessionNo++;

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

        });
        System.out.println("finished matching, total resolution size:" + edgeRepo.size() + " ,\nLast modified " + lastModified);


        return edgeRepo;
        //testAlgs(userStats);

    }

    public void writeMapToFile(JSONObject resp, String filename) {

        System.out.println("start resolution writing...");

        //  statsArray = new JSONArray();
        //needed to compute normalised weight on street display




        writeJsonToFile(filename, MAP_DATA_VARNAME, resp);
        /*try {
            GraphHttpClient.postJson("var mapList="+resp.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }




    private boolean writeJsonToFile(String filename, String varName, JSONAware jsonData) {
        BufferedWriter output;
        try {

            File file = new File(GITHUB_CLIENT_DATA_DIRECTORY + filename + ".js");
            if(!file.exists()){
                file.createNewFile();
            }
            output = new BufferedWriter(new FileWriter(file, true));

            output.write("var " + varName + "=" + jsonData.toJSONString());
            if (output != null) output.close();
            System.out.println("Finished! resulted file is " + file.getCanonicalPath());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
