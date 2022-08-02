CC =        gcc

LDFLAGS =   -lSDL2

CFLAGS =    -Wall

SOURCES =   main.c

OBJECTS =   $(SOURCES:.c=.o)

EXEC =      RType

all:        $(EXEC)

$(EXEC):    $(OBJECTS)
            $(CC) -o $(EXEC) $(CFLAGS) $(SOURCES) $(LDFLAGS)

clean:
            rm -f *~ *.o $(EXEC)

mrproper:   clean
            rm -f $(EXEC)

re:         mrproper all


