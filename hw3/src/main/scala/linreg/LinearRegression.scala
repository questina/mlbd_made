package linreg

import breeze.linalg._

class LinearRegression(learn_rate: Double = 0.1, iter: Int = 10) {
  val learning_rate: Double = learn_rate
  val iterations: Int = iter
  var W: DenseVector[Double] = _

  def fit(X: DenseMatrix[Double], Y: DenseVector[Double]): Unit = {
    val n = X.cols
    W = DenseVector.zeros[Double](n)
    for (i <- 0 until iterations) {
      update_weights(X, Y)
      println(f"iter = ${i + 1} loss = ${cost_function(X, Y)}%1.5f")
    }
  }

  def update_weights(X: DenseMatrix[Double], Y: DenseVector[Double]): Unit = {
    val m = X.rows.toDouble
    val Y_pred = predict(X)
    val dW = X.t * (Y_pred - Y) * (1.0/m)
    W = W - learning_rate * dW
  }

  def cost_function(X: DenseMatrix[Double], Y: DenseVector[Double]): Double = {
    val a = ((predict(X) - Y).t * (predict(X) - Y)) / (2.0 * Y.size)
    a
  }

  def predict(X: DenseMatrix[Double]): DenseVector[Double] = {
    X * W
  }

}
