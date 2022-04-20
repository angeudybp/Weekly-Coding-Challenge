package com.revature;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String[] projects = {"a","b","c","d","e","f"};
        String[][] dependencies= {{"a","d"},{"f","b"},{"b","d"},{"f","a"},{"d","c"}};
        System.out.println(OrderDependencies(projects,dependencies));

    }

    public static Set<String> OrderDependencies(String[] projects, String[][] dependencies){
        Set<String> dependants = new HashSet<>();
        Set<String> dependantAndProvider = new HashSet<>();
        List<String> provider = new ArrayList<>();

        Set<String> result = new LinkedHashSet<>(); //use set to avoid duplicates

        for (String[]dependency:dependencies
             ) {
            provider.add(dependency[0]);
            dependants.add(dependency[1]);
        }
        for (int i = 0; i < projects.length; i++) { //catch anything that is both a dependant and a provider
            if (dependants.contains(projects[i])&& provider.contains(projects[i])){
                dependantAndProvider.add(projects[i]);
            }
        }
        for (int i = 0; i < projects.length; i++) { //separate dependants from providers

            if (!dependants.contains(projects[i])){
                provider.add(projects[i]);
            }if(dependants.contains(projects[i])){
                if (dependantAndProvider.contains(projects[i])){
                    dependants.remove(projects[i]);
                }
                provider.remove(projects[i]);
            }
        }

        System.out.println(provider);
        System.out.println(dependantAndProvider);
        System.out.println(dependants);

        result.addAll(provider); //first add the providers
        result.addAll(dependantAndProvider); //then the dependants who are also providers
        result.addAll(dependants); //last add the ones that are only dependants

        return result;

    }



}
