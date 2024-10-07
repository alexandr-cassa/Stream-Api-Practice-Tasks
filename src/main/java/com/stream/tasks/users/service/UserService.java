package com.stream.tasks.users.service;

import com.stream.tasks.users.models.Privilege;
import com.stream.tasks.users.models.User;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserService {

    //Should return a list of first names sorted in reverse order. Lvl: Medium Tip: See Comparator
    public List<String> getFirstNamesReverseSorted(List<User> users) {
        return users.stream()
                .map(User::getFirstName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private static int compareByAgeDescAndNameAsc(User u1, User u2){
        int result = u2.getAge() - u1.getAge();
        if(result == 0){
            result = u1.getFirstName().compareTo(u2.getFirstName());
        }
        return result;
    }

    //Should sort given list of user by age descending and name ascending. Lvl: Medium
    public List<User> sortByAgeDescAndNameAsc(final List<User> users) {
        return users.stream()
                .sorted(UserService::compareByAgeDescAndNameAsc)
                .collect(Collectors.toList());
    }

    //Should return distinct list of privileges. Lvl: Easy - Medium
    public List<Privilege> getAllDistinctPrivileges(final List<User> users) {
        return users.stream()
                .flatMap(user -> user.getPrivileges().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    //Should return any user whose age greater than given. Lvl: Medium.
    public Optional<User> getUpdateUserWithAgeHigherThan(final List<User> users, final int age) {
        return users.stream()
                .filter(user -> user.getAge() > age)
                .findAny();
    }

    /*Should return a map where Key is count of privileges Value is List<User> with this count of privileges. Lvl: Thought, Need to think :)
    For Example: 1 - UserOne(DELETE)
                 3 - UserTwo(CREATE, UPDATE, DELETE)
    */
    public Map<Integer, List<User>> groupByCountOfPrivileges(final List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(
                        user -> user.getPrivileges().size(),
                        Collectors.toList()
                ));
    }

    //Should return average age for all users. Lvl: Medium. Tip: Check Primitive Streams
    public double getAverageAgeForUsers(final List<User> users) {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(-1);
    }

    //Should return most frequent last name. Lvl: Hard
    public Optional<String> getMostFrequentLastName(final List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(
                        User::getLastName,
                        Collectors.counting()
                )).entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                )).entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .filter(entry -> entry.getValue().size() == 1)
                .map(entry -> entry.getValue().get(0));
    }

    /*Should apply predicates(conditions) to all Users. Lvl: Hard. Tip: Don't forget that each Predicate actually has its method that calls test(T).
    Apply it to all users.*/
    public List<User> filterBy(final List<User> users, final Predicate<User>... predicates) {
        return users.stream()
                .filter(Arrays.stream(predicates).reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());
    }

    /*Return string with applied mapFun and Collected with delimiter.
    Lvl: Medium-Interesting. Tip: Remember namesOfTradersSortedAlphabeticallyAsOneString :D.*/
    public String convertTo(final List<User> users, final String delimiter, final Function<User, String> mapFun) {
        return users.stream()
                .map(mapFun)
                .collect(Collectors.joining(delimiter));
    }


    // Should return map where key is Privilege, value is List of User that have it. Lvl: Pretty Hard. The most hard task here.
    public Map<Privilege, List<User>> groupByPrivileges(List<User> users) {
        return users.stream()
                .flatMap(user -> user.getPrivileges().stream()
                    .map(privilege -> Pair.of(privilege, user)))
                .collect(Collectors.groupingBy(
                        Pair::getKey,
                        Collectors.mapping(Pair::getValue, Collectors.toList())
                ));
    }

    //Should return map where key is LastName, value Count of this LastName. Lvl: Medium
    public Map<String, Long> getNumberOfLastNames(final List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(User::getLastName, Collectors.counting()));
    }
}
