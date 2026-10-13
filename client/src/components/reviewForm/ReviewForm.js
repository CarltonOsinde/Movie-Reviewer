import { Form, Button } from "react-bootstrap";

const ReviewForm = ({ handleSubmit, revText, labelText, defaultValue }) => {
  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className="mb-3" controlId="reviewText">
        <Form.Label>{labelText}</Form.Label>
        <Form.Control ref={revText} as="textarea" rows={3} maxLength={2000} defaultValue={defaultValue} />
      </Form.Group>
      <Button variant="outline-info" type="submit">
        Submit
      </Button>
    </Form>
  );
};

export default ReviewForm;
