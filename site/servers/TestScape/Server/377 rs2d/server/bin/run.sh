#!/bin/bash

if [[ $1 == "" ]]; then
	echo "Usage: run.sh <mainclass[required]>"
	echo "	- where <mainclass> is the fully qualified class name of a main class on the classpath."
	exit 1
fi
ant -f ../custom-build.xml -Dmainclass=$1 run
