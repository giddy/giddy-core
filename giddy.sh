#!/bin/bash
FOLDER=".pbfs"
OSM_FILE="cluj.osm.pbf"
PBF_URL="https://s3.amazonaws.com/metro-extracts.mapzen.com/cluj_romania.osm.pbf"
 
if [ -d "$FOLDER" ];
then
   echo "FOLDER $FOLDER exist."
   wget -O $FOLDER/$OSM_FILE $PBF_URL
else
   echo "FOLDER $FOLDER does not exist" >&2
   mkdir $FOLDER
   wget -O $FOLDER/$OSM_FILE $PBF_URL
fi
