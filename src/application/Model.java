package application;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Model {

	ArrayList<Circle> points = new ArrayList<Circle>();
	ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	String[] colourNames = new String[] { "RED", "BLUE", "GREEN", "YELLOW", "PLUM" };

	public void addGroup(double originX, double originY, Integer radius, Integer pointsNumb, Pane plotPane) {
		double angle;
		double newRadius;
		double x;
		double y;
		while (pointsNumb > 0) {
			angle = Math.random() * Math.PI * 2;
			newRadius = Math.sqrt(Math.random()) * radius;
			x = originX + newRadius * Math.cos(angle);
			y = originY + newRadius * Math.sin(angle);

			if (x > 0 && x < plotPane.getWidth() && y > 0 && y < plotPane.getHeight()) {
				Circle circle = new Circle(x, y, 2);
				points.add(circle);
				plotPane.getChildren().add(circle);
				pointsNumb--;
			}
		}
	}

	public void addRectangles(Integer groupNumb, Pane plotPane) throws Exception {
		if (groupNumb > 5) {
			throw new Exception("Group numbers must be < 5");
		}
		while (groupNumb > 0) {
			int circleIndex = (int) (Math.random() * (points.size() - 1 - groupNumb));
			Circle circle = (Circle) plotPane.getChildren().get(circleIndex);

			Rectangle rect = new Rectangle(circle.getCenterX(), circle.getCenterY(), 7, 7);
			rect.setFill(Color.valueOf(colourNames[groupNumb - 1]));
			rect.setStroke(Color.BLACK);
			rect.setStrokeWidth(1.5);

			rectangles.add(rect);

			plotPane.getChildren().remove(circleIndex);
			plotPane.getChildren().add(rect);
			groupNumb--;
		}
	}

	public boolean countCentroids(Integer groupNumb) {
		boolean refreshed = false;
		for (int i = 0; i < groupNumb - 1; i++) {
			int counter = 0;
			int sumX = 0;
			int sumY = 0;
			for (Circle circle : points) {
				if (circle.getFill().equals(Color.valueOf(colourNames[i]))) {
					sumX += circle.getCenterX();
					sumY += circle.getCenterY();
					counter++;
				}
			}
			if (counter != 0) {
				for (Rectangle rectangle : rectangles) {
					if (rectangle.getFill().equals(Color.valueOf(colourNames[i])) && rectangle.getX() != sumX / counter
							&& rectangle.getY() != sumY / counter) {
						rectangle.setX(sumX / counter);
						rectangle.setY(sumY / counter);
						refreshed = true;
					}
				}
			}
		}
		if (refreshed) {
			colorPoints();
		}
		return refreshed;
	}

	public void colorPoints() {
		for (Circle circle : points) {
			double minLength = Double.MAX_VALUE;
			Rectangle closestRect = new Rectangle();
			for (Rectangle rectangle : rectangles) {
				double currLength = Math.sqrt(Math.pow(rectangle.getX() - circle.getCenterX(), 2)
						+ Math.pow(rectangle.getY() - circle.getCenterY(), 2));
				if (currLength < minLength) {
					minLength = currLength;
					closestRect = rectangle;
				}
			}
			circle.setFill(closestRect.getFill());
		}
	}

	public void clearAll(Pane plotPane) {
		points.clear();
		rectangles.clear();
		plotPane.getChildren().clear();
	}
}
