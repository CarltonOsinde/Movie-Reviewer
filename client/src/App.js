import "./App.css";
import api from "./api/axiosConfig";
import { useState, useEffect, useCallback } from "react";
import Layout from "./components/Layout";
import { Routes, Route } from "react-router-dom";
import Home from "./components/home/Home";
import Header from "./components/header/Header";
import Trailer from "./components/trailer/Trailer";
import Reviews from "./components/reviews/Reviews";
import NotFound from "./components/notFound/NotFound";

function App() {
  const [movies, setMovies] = useState([]);
  const [movie, setMovie] = useState();
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    const getMovies = async () => {
      try {
        const response = await api.get("/api/v1/movies");
        setMovies(response.data);
      } catch (error) {
        console.error("Failed to load movies", error);
      }
    };
    getMovies();
  }, []);

  const getMovieData = useCallback(async (imdbId) => {
    try {
      const response = await api.get(`/api/v1/movies/${imdbId}`);
      setMovie(response.data);
      setReviews(response.data.reviews || []);
    } catch (error) {
      console.error(`Failed to load movie ${imdbId}`, error);
    }
  }, []);

  return (
    <div className="App">
      <Header />
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home movies={movies} />} />
          <Route path="Trailer/:ytTrailerId" element={<Trailer />} />
          <Route
            path="Reviews/:movieId"
            element={
              <Reviews
                getMovieData={getMovieData}
                movie={movie}
                reviews={reviews}
                setReviews={setReviews}
              />
            }
          />
          <Route path="*" element={<NotFound />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
