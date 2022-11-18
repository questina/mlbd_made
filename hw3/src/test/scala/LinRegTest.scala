import breeze.linalg.DenseMatrix
import linreg.LinearRegression
import normalization._
import org.scalatest.funsuite.AnyFunSuite

class LinRegTest extends AnyFunSuite {
  test("read_data") {
    val (x, y) = LinRegProject.read_data("data/housing_train.csv", feature_num = 8)
    assert(x.rows == 18498)
    assert(x.cols == 9)
    assert(y.size == 18498)
  }
  test("normalization") {
    val x: DenseMatrix[Double] = DenseMatrix((1.0, 2.0, 3.0), (4.0, 5.0, 6.0))
    val scaler: StandardScaler = new StandardScaler()
    scaler.fit(x)
    val x_scaled = scaler.transform(x)
    val ans: DenseMatrix[Double] = DenseMatrix((-1.0, -1.0, -1.0), (1.0, 1.0, 1.0))
    for (i <- 0 until x.rows) {
      for (j <- 0 until x.cols) {
        assert(x_scaled(i, j) == ans(i, j))
      }
    }
  }
  test("fit") {
    val (x, y) = LinRegProject.read_data("data/housing_train.csv", feature_num = 8)
    val scaler: StandardScaler = new StandardScaler()
    scaler.fit(x)
    val x_scaled = scaler.transform(x)
    val lr: LinearRegression = new LinearRegression()
    lr.fit(x_scaled, y)
    assert(!lr.cost_function(x_scaled, y).isNaN)
  }
  test("predict") {
    val (x, y) = LinRegProject.read_data("data/housing_train.csv", feature_num = 8)
    val scaler: StandardScaler = new StandardScaler()
    scaler.fit(x)
    val x_scaled = scaler.transform(x)
    val lr: LinearRegression = new LinearRegression(iter = 50)
    lr.fit(x_scaled, y)
    var x_test = DenseMatrix((-121.98, 38.34, 18.0, 3876.0, 916.0, 2386.0, 867.0, 2.5938, 1.0))
    x_test = scaler.transform(x_test)
    println(lr.predict(x_test))
    val res = lr.predict(x_test)(0)
    assert(!res.isNaN)
    assert(res < 500000.0)
  }
}