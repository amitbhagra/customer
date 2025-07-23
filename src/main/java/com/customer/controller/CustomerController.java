try {
    // Validation logic
    validator.validate(bindDto);
} catch (ConstraintViolationException e) {
    Map<String, String> errors = new HashMap<>();
    e.getConstraintViolations().forEach(violation -> {
        errors.put(violation.getPropertyPath().toString(), violation.getMessage());
    });
    throw new ValidationException("Validation failed", errors);
}