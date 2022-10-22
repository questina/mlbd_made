#!/usr/bin/python3
import sys


def main():
    glob_cnt = 0
    glob_mean = 0
    for line in sys.stdin:
        line = line.strip()
        loc_cnt, loc_mean = map(float, line.split())
        glob_mean = (loc_cnt * loc_mean + glob_cnt * glob_mean) / (loc_cnt + glob_cnt)
        glob_cnt += loc_cnt
    print(glob_mean)


if __name__ == "__main__":
    main()
