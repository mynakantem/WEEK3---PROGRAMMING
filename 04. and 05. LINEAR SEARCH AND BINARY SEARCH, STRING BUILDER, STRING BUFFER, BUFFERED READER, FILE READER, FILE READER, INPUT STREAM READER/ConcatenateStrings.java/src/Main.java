class ConcatenateStrings {
    public static void main(String[] args) {
        String[] words = {"Hello", " ", "World", "!", " Have", " a", " great", " day."};

        String result = concatenateStrings(words);

        System.out.println("Concatenated String: " + result);
    }

    public static String concatenateStrings(String[] strings) {
        StringBuffer sb = new StringBuffer();

        for (String str : strings) {
            sb.append(str);
        }

        return sb.toString();
    }
}
