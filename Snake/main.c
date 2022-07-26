/*
 ============================================
 Name        : main.c
 Author      : Charles T.
 Version     : 1.0
 Copyright   : by default
 Description : snake game in C using ncurses
 ============================================
*/

#include "game.h"
#include <ncurses.h>
#include <stdlib.h>

WINDOW    *create_newwin(int h, int w, int y, int x)
{
  WINDOW  *local_win;

  local_win = newwin(h, w, y, x);
  box(local_win, 0 , 0); 
  wrefresh(local_win);

  return local_win;
}

int       main(int argc, char *argv[])
{
  WINDOW  *win = NULL;
  Game    game;
  int     h = 30;
  int     w = 80;
  
  initscr();
  cbreak();
  curs_set(false);
  refresh();
  win = create_newwin(h, w, (LINES - h) / 2, (COLS - w) / 2);
  keypad(win, true);
  nodelay(win, true);
  wattron(win, A_REVERSE);
  ctor_game(&game, atoi(argv[1]), w, h);
  run(&game, win, atoi(argv[2]));
  delwin(win);
  endwin();
  return 0;
}
