# Define the source file and target executable
SOURCE = Lox.java
TARGET = JLox 

# Define the Java compiler and flags
JAVAC = javac
JAVAC_FLAGS = -d bin

# Define the Java interpreter
JAVA = java

# Define directories
SRC_DIR = .
BIN_DIR = bin

# List of Java source files
SOURCES = $(wildcard $(SRC_DIR)/*.java)

# List of class files
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

# Default target
all: $(TARGET)

# Compile the Java source files
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(@D)
	$(JAVAC) $(JAVAC_FLAGS) $<

# Build the target executable
$(TARGET): $(CLASSES)
	@echo "Creating $@ executable..."

# Run the target executable with a file path argument
run: $(TARGET)
	$(JAVA) -cp $(BIN_DIR) Lox $(FILE)

# Clean the generated files
clean:
	rm -rf $(BIN_DIR)

.PHONY: all run clean
