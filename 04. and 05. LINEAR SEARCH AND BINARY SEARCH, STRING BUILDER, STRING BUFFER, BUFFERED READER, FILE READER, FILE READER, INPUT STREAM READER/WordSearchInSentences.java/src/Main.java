class WordSearchInSentences {
    public static String findSentenceWithWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.toLowerCase().contains(word.toLowerCase())) {
                return sentence;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {
                "The sky is blue.",
                "I love programming in Java.",
                "OpenAI is changing the world.",
                "This is just a test sentence."
        };

        String wordToSearch = "java";
        String result = findSentenceWithWord(sentences, wordToSearch);

        System.out.println("Result: " + result);
    }
}
