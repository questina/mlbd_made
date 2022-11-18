import breeze.linalg._
import java.io._
import linreg.LinearRegression
import normalization.StandardScaler

object LinRegProject{

  def read_data(filename: String, feature_num: Int = 1): (DenseMatrix[Double], DenseVector[Double]) = {
    val bufferedSource = io.Source.fromFile(filename)
    val lines = bufferedSource.getLines().toList
    val m = lines.size - 1
    val n = feature_num
    val x: DenseMatrix[Double] = DenseMatrix.ones[Double](m, n + 1)
    val y: DenseVector[Double] = DenseVector.zeros[Double](m)
    for (i <- 1 until m + 1) {
      val cols = lines(i).split(",").map(_.trim)
      for (j <- 0 until n) {
        if (cols(j) != "") {
          x(i - 1, j) = cols(j).toDouble
        }
      }
      y(i - 1) = cols(n).toDouble
    }
    bufferedSource.close
    (x, y)
  }

  def write_data(filename: String, y: DenseVector[Double]): Unit = {
    val out = new PrintWriter(filename, "UTF-8")
    for (i <- 0 until y.size) {
      out.println(f"${y(i)}")
    }
    out.close()
  }

  def main(args: Array[String]): Unit = {
    val train_filename = args(0)
    val test_filename = args(1)
    val predict_filename = args(2)
    println(f"train_file = ${train_filename}\ntest_file = ${test_filename}\npredict_file = ${predict_filename}")
    var (x_train, y_train) = read_data(train_filename, 8)
    println(f"read ${x_train.rows} rows of train data")
    var (x_test, y_test) = read_data(test_filename, 8)
    println(f"read ${x_test.rows} rows of test data")
    val scaler: StandardScaler = new StandardScaler()
    scaler.fit(x_train)
    x_train = scaler.transform(x_train)
    x_test = scaler.transform(x_test)
    println(f"normalized data with StandardScaler")
    val lr: LinearRegression = new LinearRegression(learn_rate = 1.0, iter = 50)
    lr.fit(x_train, y_train)
    println("linear regression is fitted")
    write_data(predict_filename, lr.predict(x_test))
    println("saved predictions")
  }
}