/*
 ============================================
 Name        : game.h
 Author      : Charles T.
 Version     : 1.0
 Copyright   : by default
 Description : snake game in C using ncurses
 ============================================
*/

#ifndef _GAME_H_
#define _GAME_H_

#include "entity.h"
#include <ncurses.h>

#define ROWS        30
#define COLUMNS     80
#define SIZE_SNAKE  256

typedef enum  Direction {NORTH, EAST, SOUTH, WEST} Direction;

typedef struct {
  bool      life;
  int       right_limit;
  int       bottom_limit;
  int       length;
  Entity    food;
  Entity    snake[SIZE_SNAKE];
  Entity    tail;
  Direction direction;
} Game;


void  ctor_game(Game *, int, int, int);
void  run(Game *, WINDOW *, long);

#endif
