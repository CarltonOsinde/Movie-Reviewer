import { Link } from "react-router-dom";

const NotFound = () => {
  return (
    <div className="text-center mt-5">
      <h3>Page not found</h3>
      <Link to="/">Back to movies</Link>
    </div>
  );
};

export default NotFound;
