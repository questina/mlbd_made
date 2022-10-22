import sys


def main():
    cnt = 0
    mean = 0
    var = 0
    # Calculating mean
    for line in sys.stdin:
        fields = line.split(",")
        if len(fields) < 10:
            continue
        price = fields[9]
        try:
            price = float(price)
        except ValueError:
            continue
        mean += price
        cnt += 1
    mean = mean / cnt
    print("mean = ", mean)
    # Calculating variance
    sys.stdin.seek(0)
    for line in sys.stdin:
        fields = line.split(",")
        if len(fields) < 10:
            continue
        price = fields[9]
        try:
            price = float(price)
        except ValueError:
            continue
        var += (price - mean) ** 2
    print("var = ", var / cnt)


if __name__ == "__main__":
    main()
