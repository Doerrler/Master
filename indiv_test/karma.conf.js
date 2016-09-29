//jshint strict: false
module.exports = function(config) {
  config.set({

    basePath: './app',
	frameworks: ['jasmine'],
    files: [
      'lib/angular.min.js',      
      'lib/angular-mocks.js',
	  'todo.js',	  
      'unit_tests/todo_test.js'
    ],
	exclude: [
	],
	preprocessors: {
	 	 'todo.js' : ['coverage']
	},
	reporters: ['progress','coverage'],
	coverageReporter: {
			type: 'html',
			dir: 'coverage/',
			subdir: '.'
	},
	port: 8080,
	colors: true,
    browsers: ['Chrome'],
	singleRun: true,
    plugins: [
      'karma-chrome-launcher',      
      'karma-jasmine',
	  'karma-coverage'
    ]    

  });
};
