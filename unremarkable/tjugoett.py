#!/usr/bin/env python3
import random


class Player:
    def __init__(self, player_number):
        self.name = f"Player {player_number}"
        self.playing = True
        self.points = 0

    def draw_card(self, deck):
        if not self.playing:
            print(f"{self.name} is out of the game.")
            return

        print(f"{self.name}'s turn, draw a card or stop? You have {self.points} points.")

        while True:
            print("Draw a card? [y/n]")
            i = input("> ")

            if (i[0].lower() == "y"):
                break
            elif (i[0].lower() == "n"):
                print(f"{self.name} is out of the game with {self.points} points.")
                self.playing = False
                return

        card = deck.pop()
        self.points += card
        if self.points > 21:
            self.playing = False
            print(f"{self.name} drew a card word {card}.")
            print(f"At {self.points} point they're out of the game.")

        print(f"{self.name} now has {self.points} points")

    def __str__(self):
        return f"{self.name} ({self.points} points)"

    def __repr__(self):
        return f"<self.__str__()>"


def get_number_of_players():
    while True:
        print("How many players?")
        players = input("> ")
        if players.isdigit():
            return int(players)


def game_over(players, deck):
    all_players_out = any([not player.playing for player in players])
    deck_empty = len(deck) == 0
    return all_players_out or deck_empty


def play():
    players = [Player(i) for i in range(1, get_number_of_players() + 1)]
    deck = list(range(1, 14)) * 4
    random.shuffle(deck)

    while True:
        for player in players:
            if game_over(players, deck):
                print("Game over!")
                print("\n".join([str(player) for player in players]))
                return
            player.draw_card(deck)
            print()  # new line


if __name__ == "__main__":
    play()
