.PHONY: clean all server


clean:
	lein clean

all:
	lein cljsbuild once min

server:
	cd `pwd`/resources/public; \
	python -m SimpleHTTPServer 3449

