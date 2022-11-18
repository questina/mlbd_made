package normalization

import breeze.linalg._
import breeze.stats.mean

import scala.collection.mutable.ListBuffer

class StandardScaler {
  var means: List[Double] = List()
  var stds: List[Double] = List()

  def fit(X: DenseMatrix[Double]): Unit = {
    var X_mean = 0.0
    var X_std = 0.0
    for (j <- 0 until X.cols) {
      X_mean = mean(X(::, j))
      X_std = sum((X(::, j) - X_mean) *:* (X(::, j) - X_mean)) / X.cols
      means = means :+ X_mean
      stds = stds :+ X_std
    }
  }

  def transform(X: DenseMatrix[Double]): DenseMatrix[Double] = {
    for (j <- 0 until X.cols) {
      if (stds(j) != 0) {
        X(::, j) -= means(j)
        X(::, j) /= stds(j)
      }
    }
    X
  }
}
