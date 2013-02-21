JAVASRC    = Ball.java BallHeap.java CollidingBalls.java Vector.java Wall.java
ALLSOURCE  = ${JAVASRC} Makefile README
MAINCLASS  = CollidingBalls
CLASSES    = ${JAVASRC:.java=.class}
JARCLASSES = ${CLASSES}
JARFILE    = CollidingBalls
SUBMITDIR  = cmps101-mw.w13 prog2

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
