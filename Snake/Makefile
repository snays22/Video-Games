LDFLAGS=        -lncurses

CFLAGS=         -Wall

CC=             gcc

SRCS=           main.c \
                game.c

OBJS=           $(SRCS:.c=.o)

EXEC=           snake



all:            $(EXEC)

$(EXEC):        $(OBJETS)
                $(CC) -o $(EXEC) $(CFLAGS) $(SRCS) $(LDFLAGS)

clean:
                rm -f *~ *.o $(EXEC)

mrproper:       clean
                rm -f $(EXEC)

re:             mrproper all
