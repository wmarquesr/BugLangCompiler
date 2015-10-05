eclipse:
	@gradle eclipse

javadoc:
	@gradle assemble javadoc

build:
	@gradle build

run:
	@gradle assemble run

test:
	@gradle assemble test

clean:
	@gradle clean cleanEclipse
