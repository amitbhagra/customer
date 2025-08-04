// Add validation at the beginning of readWithMessageConverters
if (input == null) {
    throw new IllegalArgumentException("Input cannot be null");
}

// Add additional validation based on the specific requirements
if (input.isEmpty()) {
    throw new IllegalArgumentException("Input cannot be empty");
}