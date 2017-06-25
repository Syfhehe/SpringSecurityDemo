require.config({

    baseUrl: "/js",

    paths: {
        "jquery": "lib/jquery-1.11.1",
        "timepicker": "lib/jquery.timepicker",
        "validate": "lib/jquery.validate",
        "validateAdditional": "lib/additional-methods",
        "autoNumeric": "lib/autoNumeric",
        "jqueryui": "lib/jquery-ui",
        "jqte": "lib/jquery-te-1.4.0",
        "bootstrap": "lib/bootstrap"
    },

    shim: {
        "jquery": {
            exports: "jquery"
        },
        "timepicker": {
            exports: "timepicker"
        },
        "autoNumeric": {
            exports: "autoNumeric",
            deps: ['jquery']
        },
        "jqte": {
            exports: "jqte",
            deps: ['jquery']
        },
        "validate": {
            exports: "validate",
            deps: ['jquery']
        },
        "validateAdditional": {
            exports: "validateAdditional",
            deps: ['validate']
        },        
        "jqueryui": {
            exports: "jqueryui",
            deps: ['jquery']
        },
        "bootstrap": {
            exports: "bootstrap",
            deps: ['jquery']
        }
    }
            
});
