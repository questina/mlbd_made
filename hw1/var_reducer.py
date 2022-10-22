#!/usr/bin/python3
import sys


def main():
    glob_cnt = 0
    glob_mean = 0
    glob_var = 0
    for line in sys.stdin:
        line = line.strip()
        loc_cnt, loc_mean, loc_var = map(float, line.split())
        glob_mean = (loc_cnt * loc_mean + glob_cnt * glob_mean) / (loc_cnt + glob_cnt)
        glob_var = (glob_cnt * glob_var + loc_cnt * loc_var) / (glob_cnt + loc_cnt)
        glob_var += glob_cnt * loc_cnt * ((glob_mean - loc_mean) / (glob_cnt + loc_cnt)) ** 2
        glob_cnt += loc_cnt
    print(glob_var)


if __name__ == "__main__":
    main()
