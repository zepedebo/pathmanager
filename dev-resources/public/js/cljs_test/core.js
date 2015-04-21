// Compiled by ClojureScript 0.0-2371
goog.provide('cljs_test.core');
goog.require('cljs.core');
cljs_test.core.foo = (function foo(greeting){if(cljs.core.truth_(greeting))
{return (''+cljs.core.str.cljs$core$IFn$_invoke$arity$1(greeting)+"ClojureScript!");
} else
{return ("Hello, ClojureScript!");
}
});
document.write(cljs_test.core.foo.call(null,"Welcome to "));
