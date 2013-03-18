JAVASRC    = node.java RedBlackTree.java Tree.java
ALLSOURCE  = ${JAVASRC} Makefile README
MAINCLASS  = RedBlackTree
CLASSES    = ${JAVASRC:.java=.class}
JARCLASSES = ${CLASSES}
JARFILE    = RedBlackTree
SUBMITDIR  = cmps101-mw.w13 prog4

all : ${JARFILE}

${JARFILE} : ${CLASSES}
	echo Main-class: ${MAINCLASS} >Manifest
	jar cvfm ${JARFILE} Manifest ${JARCLASSES}
	- rm Manifest
	chmod +x ${JARFILE}

%.class : %.java
	javac -cp . *.java

clean :
	- rm ${JARCLASSES}

spotless : clean
	- rm ${JARFILE}

ci : ${ALLSOURCE}
	checksource ${ALLSOURCE}
	cid + ${ALLSOURCE}

submit : ${ALLSOURCE}
	submit ${SUBMITDIR} ${ALLSOURCE}

again : ${ALLSOURCE}
	make spotless ci all lis
