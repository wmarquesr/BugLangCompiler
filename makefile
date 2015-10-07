eclipse:
	@gradle eclipse

javadoc:
	@gradle assemble javadoc

run:
	@gradle assemble run

test:
	@gradle assemble test

.PHONY: clean
clean:
	@gradle clean cleanEclipse
