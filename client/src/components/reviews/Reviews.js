import { useEffect, useRef, useState } from "react";
import { useParams } from "react-router-dom";
import { Container, Row, Col } from "react-bootstrap";
import api from "../../api/axiosConfig";
import ReviewForm from "../reviewForm/ReviewForm";

const Reviews = ({ getMovieData, movie, reviews, setReviews }) => {
  const revText = useRef();
  const { movieId } = useParams();
  const [error, setError] = useState("");

  useEffect(() => {
    getMovieData(movieId);
  }, [getMovieData, movieId]);

  const addReview = async (e) => {
    e.preventDefault();
    const reviewBody = revText.current.value.trim();
    if (!reviewBody) {
      setError("Please write something before submitting.");
      return;
    }

    try {
      const response = await api.post("/api/v1/reviews", { reviewBody, imdbId: movieId });
      setReviews([...reviews, response.data]);
      revText.current.value = "";
      setError("");
    } catch (err) {
      setError(err.response?.data?.message || "Could not save your review. Please try again.");
    }
  };

  return (
    <Container>
      <Row>
        <Col>
          <h3>{movie?.title ? `Reviews: ${movie.title}` : "Reviews"}</h3>
        </Col>
      </Row>
      <Row className="mt-2">
        <Col>
          {movie?.poster && <img src={movie.poster} alt={`${movie.title} poster`} />}
        </Col>
        <Col>
          <ReviewForm handleSubmit={addReview} revText={revText} labelText="Write a review" />
          {error && <p className="text-warning mt-2">{error}</p>}
          <hr />
          {reviews.length === 0 && <p>No reviews yet. Be the first.</p>}
          {reviews.map((review, index) => (
            <div key={review.id || index}>
              <p>{review.body}</p>
              <hr />
            </div>
          ))}
        </Col>
      </Row>
    </Container>
  );
};

export default Reviews;
