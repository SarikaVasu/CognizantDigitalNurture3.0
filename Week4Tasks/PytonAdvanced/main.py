from text_processing_tool import count_words, convert_to_upper, find_unique_words

def main():
    text = input("Enter a text string:")
    word_count = count_words(text)
    unique_words = find_unique_words(text)
    upperCase = convert_to_upper(text)
    print(f"Word count: {word_count}")
    print(f"Unique Words: {unique_words}")
    print(f"Upper case text: {upperCase}")

if __name__ == "__main__":
    main()