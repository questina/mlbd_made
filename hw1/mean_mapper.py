#!/usr/bin/python3
import sys


def cnt_mean(mean, chunk_num, price):
    return (chunk_num * mean + price) / (chunk_num + 1)


def main():
    chunk_num = 0
    mean = 0
    for line in sys.stdin:
        fields = line.split(",")
        if len(fields) < 10:
            continue
        price = fields[9]
        try:
            price = float(price)
        except ValueError:
            continue
        mean = cnt_mean(mean=mean, chunk_num=chunk_num, price=price)
        chunk_num += 1
        print(str(chunk_num) + " " + str(mean))


if __name__ == "__main__":
    main()
