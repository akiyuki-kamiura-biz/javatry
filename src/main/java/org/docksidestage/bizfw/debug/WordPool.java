package org.docksidestage.bizfw.debug;

import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * @author zaya
 */
public class WordPool {
    private final Map<Long, Word> wordMap;

    public WordPool() {
        LanguagePool languagePool = new LanguagePool();
        wordMap = new HashMap<>();
        wordMap.put(1L, new Word(getJapanese(languagePool), "私"));
        wordMap.put(2L, new Word(getJapanese(languagePool), "柿"));
        wordMap.put(3L, new Word(getJapanese(languagePool), "荼"));
        wordMap.put(4L, new Word(getJapanese(languagePool), "昂"));
    }

    public Map.Entry<Long, Word> create(Language language, String word) {
        Long id = incrementId();
        wordMap.put(id, new Word(language, word));
        return new AbstractMap.SimpleEntry<>(id, find(id));
    }

    public Word find(String word) {
        return wordMap.values().stream().filter(v -> v.getWord().equals(word)).findFirst().orElseThrow(NoSuchElementException::new);
    }

    public Long findId(String word) {
        return wordMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getWord().equals(word))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No value present, word:" + word));
    }

    public Word find(Long id) {
        return wordMap.get(id);
    }

    public Word update(Long id, Word word) {
        wordMap.remove(id);
        wordMap.put(id, word);
        return wordMap.get(id);
    }

    public Word update(String oldWord, String newWord) {
        Long id = findId(oldWord);
        Word word = wordMap.get(id);
        wordMap.remove(id);
        wordMap.put(id, new Word(word.getLanguage(), newWord));
        return wordMap.get(id);
    }

    public Word update(String language, String oldWord, String newWord) {
        Long id = findId(oldWord);
        wordMap.remove(id);
        wordMap.put(id, new Word(new Language(language), newWord));
        return wordMap.get(id);
    }

    public String replace(Long id, String oldWord, String newWord) {
        Word targetWord = wordMap.get(id);
        String replacedStr = targetWord.getWord().replace(oldWord, newWord);
        update(targetWord.getLanguage().getName(), targetWord.getWord(), replacedStr);
        return replacedStr;
    }

    public void delete(Long id) {
        wordMap.remove(id);
    }

    public List<Word> getWords() {
        return new ArrayList<>(wordMap.values());
    }

    public List<String> getWordsOnly() {
        return wordMap.values().stream().map(Word::getWord).collect(Collectors.toList());
    }

    public List<Language> getLanguages() {
        List<Language> languages = new ArrayList<>();
        wordMap.forEach((id, word) -> {
            if (!word.hasLanguage()) {
                throw new IllegalStateException("言語がないよ〜 word: " + word);
            }
            languages.add(word.getLanguage());
        });
        return languages;
    }

    private Long incrementId() {
        return findMaxId() + 1;
    }

    private Language getJapanese(LanguagePool languagePool) {
        return languagePool.getLanguage("Japanese");
    }

    private Long findMaxId() {
        return wordMap.keySet().stream().mapToLong(k -> k).max().orElseThrow(NoSuchElementException::new);
    }
}
