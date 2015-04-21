// Compiled by ClojureScript 0.0-2371
goog.provide('cljs_test.core_test');
goog.require('cljs.core');
goog.require('cljs_test.core');
goog.require('cljs_test.core');
goog.require('cemerick.cljs.test');
goog.require('cemerick.cljs.test');
cljs_test.core_test.foo_test = (function foo_test(){return cemerick.cljs.test.test_function.call(null,cemerick.cljs.test.init_test_environment.call(null),cljs_test.core_test.foo_test);
});
cljs_test.core_test.foo_test = cljs.core.with_meta.call(null,cljs_test.core_test.foo_test,cljs.core.merge.call(null,new cljs.core.PersistentArrayMap(null, 5, [new cljs.core.Keyword(null,"end-column","end-column",1425389514),19,new cljs.core.Keyword(null,"end-line","end-line",1837326455),8,new cljs.core.Keyword(null,"column","column",2078222095),10,new cljs.core.Keyword(null,"line","line",212345235),8,new cljs.core.Keyword(null,"file","file",-1269645878),"/Users/steveg/Projects/cljs-test/test/cljs/cljs_test/core_test.cljs"], null),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"name","name",1843675177),cljs.core.with_meta.call(null,new cljs.core.Symbol("cljs-test.core-test","foo-test","cljs-test.core-test/foo-test",1831098226,null),new cljs.core.PersistentArrayMap(null, 5, [new cljs.core.Keyword(null,"end-column","end-column",1425389514),19,new cljs.core.Keyword(null,"end-line","end-line",1837326455),8,new cljs.core.Keyword(null,"column","column",2078222095),10,new cljs.core.Keyword(null,"line","line",212345235),8,new cljs.core.Keyword(null,"file","file",-1269645878),"/Users/steveg/Projects/cljs-test/test/cljs/cljs_test/core_test.cljs"], null)),new cljs.core.Keyword(null,"test","test",577538877),(function foo_test_test(test_ctx__5186__auto__){var _test_ctx = test_ctx__5186__auto__;var async_QMARK___5101__auto__ = new cljs.core.Keyword(null,"async","async",1050769601).cljs$core$IFn$_invoke$arity$1(cljs.core.meta.call(null,new cljs.core.Keyword(null,"test-name","test-name",-675595913).cljs$core$IFn$_invoke$arity$1(_test_ctx)));var _STAR_test_ctx_STAR_5361 = cemerick.cljs.test._STAR_test_ctx_STAR_;try{cemerick.cljs.test._STAR_test_ctx_STAR_ = (cljs.core.truth_(async_QMARK___5101__auto__)?null:_test_ctx);
try{try{cljs.core.swap_BANG_.call(null,new cljs.core.Keyword(null,"test-env","test-env",-540228992).cljs$core$IFn$_invoke$arity$1(_test_ctx),cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("cemerick.cljs.test","test-contexts","cemerick.cljs.test/test-contexts",1505935128)], null),cljs.core.conj,"I don't do a lot\n");
try{cljs.core.swap_BANG_.call(null,new cljs.core.Keyword(null,"test-env","test-env",-540228992).cljs$core$IFn$_invoke$arity$1(_test_ctx),cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("cemerick.cljs.test","test-contexts","cemerick.cljs.test/test-contexts",1505935128)], null),cljs.core.conj,"Edge cases\n");
try{cljs.core.swap_BANG_.call(null,new cljs.core.Keyword(null,"test-env","test-env",-540228992).cljs$core$IFn$_invoke$arity$1(_test_ctx),cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("cemerick.cljs.test","test-contexts","cemerick.cljs.test/test-contexts",1505935128)], null),cljs.core.conj,"(foo str)");
var _test_ctx_5369__$1 = _test_ctx;var async_QMARK___5101__auto___5370__$1 = new cljs.core.Keyword(null,"async","async",1050769601).cljs$core$IFn$_invoke$arity$1(cljs.core.meta.call(null,new cljs.core.Keyword(null,"test-name","test-name",-675595913).cljs$core$IFn$_invoke$arity$1(_test_ctx_5369__$1)));var _STAR_test_ctx_STAR_5363_5371 = cemerick.cljs.test._STAR_test_ctx_STAR_;try{cemerick.cljs.test._STAR_test_ctx_STAR_ = (cljs.core.truth_(async_QMARK___5101__auto___5370__$1)?null:_test_ctx_5369__$1);
try{try{var values__5121__auto___5372 = cljs.core._conj.call(null,cljs.core._conj.call(null,cljs.core.List.EMPTY,cljs_test.core.foo.call(null,"")),"ClojureScript!");var result__5122__auto___5373 = cljs.core.apply.call(null,cljs.core._EQ_,values__5121__auto___5372);if((result__5122__auto___5373 instanceof cemerick.cljs.test.TestContext))
{throw (new Error("TestContext provided as [form] in `is` assertion. If using `is` with an explicit test context, use the 3-arg arity."));
} else
{}
if(cljs.core.truth_(result__5122__auto___5373))
{cemerick.cljs.test.do_report.call(null,_test_ctx_5369__$1,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"type","type",1174270348),new cljs.core.Keyword(null,"pass","pass",1574159993),new cljs.core.Keyword(null,"expected","expected",1583670997),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1501502141,null),"ClojureScript!",cljs.core.list(new cljs.core.Symbol(null,"foo","foo",-1385541733,null),"")),new cljs.core.Keyword(null,"actual","actual",107306363),cljs.core.cons.call(null,cljs.core._EQ_,values__5121__auto___5372),new cljs.core.Keyword(null,"message","message",-406056002),null], null));
} else
{cemerick.cljs.test.do_report.call(null,_test_ctx_5369__$1,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"type","type",1174270348),new cljs.core.Keyword(null,"fail","fail",1706214930),new cljs.core.Keyword(null,"expected","expected",1583670997),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1501502141,null),"ClojureScript!",cljs.core.list(new cljs.core.Symbol(null,"foo","foo",-1385541733,null),"")),new cljs.core.Keyword(null,"actual","actual",107306363),cljs.core._conj.call(null,cljs.core._conj.call(null,cljs.core.List.EMPTY,cljs.core.cons.call(null,new cljs.core.Symbol(null,"=","=",-1501502141,null),values__5121__auto___5372)),new cljs.core.Symbol(null,"not","not",1044554643,null)),new cljs.core.Keyword(null,"message","message",-406056002),null], null));
}
}catch (e5365){if((e5365 instanceof Error))
{var t__5158__auto___5374 = e5365;cemerick.cljs.test.do_report.call(null,_test_ctx_5369__$1,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"type","type",1174270348),new cljs.core.Keyword(null,"error","error",-978969032),new cljs.core.Keyword(null,"expected","expected",1583670997),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1501502141,null),"ClojureScript!",cljs.core.list(new cljs.core.Symbol(null,"foo","foo",-1385541733,null),"")),new cljs.core.Keyword(null,"actual","actual",107306363),t__5158__auto___5374,new cljs.core.Keyword(null,"message","message",-406056002),null], null));
} else
{throw e5365;

}
}}catch (e5364){if((e5364 instanceof Error))
{var e__5102__auto___5375 = e5364;if(cljs.core.truth_(async_QMARK___5101__auto___5370__$1))
{cemerick.cljs.test.done_STAR_.call(null,_test_ctx_5369__$1,e__5102__auto___5375);
} else
{throw e__5102__auto___5375;
}
} else
{throw e5364;

}
}}finally {cemerick.cljs.test._STAR_test_ctx_STAR_ = _STAR_test_ctx_STAR_5363_5371;
}var _test_ctx__$1 = _test_ctx;var async_QMARK___5101__auto____$1 = new cljs.core.Keyword(null,"async","async",1050769601).cljs$core$IFn$_invoke$arity$1(cljs.core.meta.call(null,new cljs.core.Keyword(null,"test-name","test-name",-675595913).cljs$core$IFn$_invoke$arity$1(_test_ctx__$1)));var _STAR_test_ctx_STAR_5366 = cemerick.cljs.test._STAR_test_ctx_STAR_;try{cemerick.cljs.test._STAR_test_ctx_STAR_ = (cljs.core.truth_(async_QMARK___5101__auto____$1)?null:_test_ctx__$1);
try{try{var values__5121__auto__ = cljs.core._conj.call(null,cljs.core._conj.call(null,cljs.core.List.EMPTY,cljs_test.core.foo.call(null,null)),"Hello, ClojureScript!");var result__5122__auto__ = cljs.core.apply.call(null,cljs.core._EQ_,values__5121__auto__);if((result__5122__auto__ instanceof cemerick.cljs.test.TestContext))
{throw (new Error("TestContext provided as [form] in `is` assertion. If using `is` with an explicit test context, use the 3-arg arity."));
} else
{}
if(cljs.core.truth_(result__5122__auto__))
{cemerick.cljs.test.do_report.call(null,_test_ctx__$1,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"type","type",1174270348),new cljs.core.Keyword(null,"pass","pass",1574159993),new cljs.core.Keyword(null,"expected","expected",1583670997),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1501502141,null),"Hello, ClojureScript!",cljs.core.list(new cljs.core.Symbol(null,"foo","foo",-1385541733,null),null)),new cljs.core.Keyword(null,"actual","actual",107306363),cljs.core.cons.call(null,cljs.core._EQ_,values__5121__auto__),new cljs.core.Keyword(null,"message","message",-406056002),null], null));
} else
{cemerick.cljs.test.do_report.call(null,_test_ctx__$1,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"type","type",1174270348),new cljs.core.Keyword(null,"fail","fail",1706214930),new cljs.core.Keyword(null,"expected","expected",1583670997),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1501502141,null),"Hello, ClojureScript!",cljs.core.list(new cljs.core.Symbol(null,"foo","foo",-1385541733,null),null)),new cljs.core.Keyword(null,"actual","actual",107306363),cljs.core._conj.call(null,cljs.core._conj.call(null,cljs.core.List.EMPTY,cljs.core.cons.call(null,new cljs.core.Symbol(null,"=","=",-1501502141,null),values__5121__auto__)),new cljs.core.Symbol(null,"not","not",1044554643,null)),new cljs.core.Keyword(null,"message","message",-406056002),null], null));
}
return result__5122__auto__;
}catch (e5368){if((e5368 instanceof Error))
{var t__5158__auto__ = e5368;return cemerick.cljs.test.do_report.call(null,_test_ctx__$1,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"type","type",1174270348),new cljs.core.Keyword(null,"error","error",-978969032),new cljs.core.Keyword(null,"expected","expected",1583670997),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1501502141,null),"Hello, ClojureScript!",cljs.core.list(new cljs.core.Symbol(null,"foo","foo",-1385541733,null),null)),new cljs.core.Keyword(null,"actual","actual",107306363),t__5158__auto__,new cljs.core.Keyword(null,"message","message",-406056002),null], null));
} else
{throw e5368;

}
}}catch (e5367){if((e5367 instanceof Error))
{var e__5102__auto__ = e5367;if(cljs.core.truth_(async_QMARK___5101__auto____$1))
{return cemerick.cljs.test.done_STAR_.call(null,_test_ctx__$1,e__5102__auto__);
} else
{throw e__5102__auto__;
}
} else
{throw e5367;

}
}}finally {cemerick.cljs.test._STAR_test_ctx_STAR_ = _STAR_test_ctx_STAR_5366;
}}finally {cljs.core.swap_BANG_.call(null,new cljs.core.Keyword(null,"test-env","test-env",-540228992).cljs$core$IFn$_invoke$arity$1(_test_ctx),cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("cemerick.cljs.test","test-contexts","cemerick.cljs.test/test-contexts",1505935128)], null),cljs.core.pop);
}}finally {cljs.core.swap_BANG_.call(null,new cljs.core.Keyword(null,"test-env","test-env",-540228992).cljs$core$IFn$_invoke$arity$1(_test_ctx),cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("cemerick.cljs.test","test-contexts","cemerick.cljs.test/test-contexts",1505935128)], null),cljs.core.pop);
}}finally {cljs.core.swap_BANG_.call(null,new cljs.core.Keyword(null,"test-env","test-env",-540228992).cljs$core$IFn$_invoke$arity$1(_test_ctx),cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("cemerick.cljs.test","test-contexts","cemerick.cljs.test/test-contexts",1505935128)], null),cljs.core.pop);
}}catch (e5362){if((e5362 instanceof Error))
{var e__5102__auto__ = e5362;if(cljs.core.truth_(async_QMARK___5101__auto__))
{return cemerick.cljs.test.done_STAR_.call(null,_test_ctx,e__5102__auto__);
} else
{throw e__5102__auto__;
}
} else
{throw e5362;

}
}}finally {cemerick.cljs.test._STAR_test_ctx_STAR_ = _STAR_test_ctx_STAR_5361;
}})], null)));
cemerick.cljs.test.register_test_BANG_.call(null,new cljs.core.Symbol(null,"cljs-test.core-test","cljs-test.core-test",510252731,null),new cljs.core.Symbol("cljs-test.core-test","foo-test","cljs-test.core-test/foo-test",1831098226,null),cljs_test.core_test.foo_test);
